package net.snapecraft.playtimetrackercloud;

import org.bukkit.entity.Player;

import java.sql.*;
import java.util.HashMap;
import java.util.UUID;

public class Api {

    public static HashMap<Player, Long> joinList = new HashMap<>();

    /**
     * Execute an SQL query that does not return anything.
     * @param query the query String
     */
    public static void executeSQL(String query) {
        String url = "jdbc:mysql://" + Config.getDBHost() + ":" + Config.getDBPort() + "/" + Config.getDBName() + "?useSSL=" + Config.getDBuseSSL();
        String user = Config.getDBUser();
        String password = Config.getDBPassword();

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            st.execute(query);
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * Execute an SQL query that returns a String
     * @param query the query String
     * @return the ResultSet.
     */
    public static String executeQuery(String query) {

        String url = "jdbc:mysql://" + Config.getDBHost() + ":" + Config.getDBPort() + "/" + Config.getDBName() + "?useSSL=" + Config.getDBuseSSL();
        String user = Config.getDBUser();
        String password = Config.getDBPassword();

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println("Could not connect to the database. Did you set the config settings correctly?");
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public static Boolean getExists(UUID uuid) {
//        System.out.println("getExists");
//        System.out.println("SELECT " + Config.getTimeColumn() + " FROM " + Config.getDBTable() + " WHERE player='" + uuid.toString() + "';");
        return executeQuery("SELECT " + Config.getTimeColumn() + " FROM " + Config.getDBTable() + " WHERE player='" + uuid.toString() + "';").length() != 0;
    }

    public static Long getPlaytime(UUID uuid) {
//        System.out.println("getPlaytime");
        String result = executeQuery("SELECT " + Config.getTimeColumn() + " FROM " + Config.getDBTable() + " WHERE player='" + uuid.toString() + "';");
        if(getExists(uuid)) {
            return Long.parseLong(result);
        } else {
            return 0L;
        }
    }

    public static void setPlaytime(UUID uuid, Long playtime) {
//        System.out.println("setPlaytime");
        if(getExists(uuid)) {
//            System.out.println("UPDATE " + Config.getDBTable() + " SET " + Config.getTimeColumn() + "='" + playtime + "' WHERE player='" + uuid.toString() + "';");
            executeSQL("UPDATE " + Config.getDBTable() + " SET " + Config.getTimeColumn() + "='" + playtime + "' WHERE player='" + uuid.toString() + "';");
        } else {
            executeSQL("INSERT INTO " + Config.getDBTable() + " (player, " + Config.getTimeColumn() + ") VALUES ('" + uuid.toString() + "', '" + playtime + "');");
        }
    }

    public static void addPlaytime(UUID uuid, Long playtimeToAdd) {
//        System.out.println("addPlaytime");
        setPlaytime(uuid, getPlaytime(uuid) + playtimeToAdd);
    }


}

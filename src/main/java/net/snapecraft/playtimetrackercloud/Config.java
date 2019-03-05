package net.snapecraft.playtimetrackercloud;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Config {

    public static File ConfigFile = new File("plugins/PlayTimeTracker", "config.yml");
    public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);

    public static void save() throws IOException {
        Config.save(ConfigFile);

    }
    public static void reload() {
        Config = YamlConfiguration.loadConfiguration(ConfigFile);

    }

    public static void setDefaults() {
        Config.options().header("PlayTimeTracker Config  |  github.com/MayusYT/PlayTimeTracker\n" +
                                "Please configure your database settings. Please use a table that has the same name as the server (bungee.yml)\n" +
                                "I recommend you that you should leave 'useSSL' disabled.\n" +
                                "Column: Column the plugin should write the playtime in. Leave as default if you created the database with the GitHub tutorial.");

        Config.addDefault("database.host", "localhost");
        Config.addDefault("database.port", "3306");
        Config.addDefault("database.db", "playtimetracker");
        Config.addDefault("database.username", "root");
        Config.addDefault("database.password", "");
        Config.addDefault("database.column", "playtime");
        Config.addDefault("database.table", "vanilla");
        Config.addDefault("database.useSSL", "false");
        Config.addDefault("database.cloudRankColumn", "rank");

        Config.options().copyDefaults(true);
    }

    public static String getDBHost() {
        return Config.getString("database.host");
    }
    public static String getDBPort() {
        return Config.getString("database.port");
    }
    public static String getDBUser() {
        return Config.getString("database.username");
    }
    public static String getDBPassword() {
        return Config.getString("database.password");
    }
    public static String getDBName() {
        return Config.getString("database.db");
    }
    public static String getDBuseSSL() {
        return Config.getString("database.useSSL");
    }
    public static String getTimeColumn() {
        return Config.getString("database.column");
    }
    public static String getDBTable() {
        return Config.getString("database.table");
    }
    public static String getCloudColumn() { return Config.getString("database.cloudRankColumn"); }
}

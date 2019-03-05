package net.snapecraft.playtimetrackercloud;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.api.player.PermissionProvider;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import org.bukkit.entity.Player;

public class CloudApi {

    public static void updateCloud(Player p) {
        String group = PermissionProvider.getPlayerGroups(CloudAPI.getInstance().getOfflinePlayer(p.getUniqueId()));
        if(Api.getExists(p.getUniqueId())) {
            Api.executeSQL("UPDATE " + Config.getDBTable() + " SET " + Config.getCloudColumn() + "='" + group + "' WHERE player='" + p.getUniqueId().toString() + "';");
        } else {
            Api.executeSQL("INSERT INTO " + Config.getDBTable() + " (player, " + Config.getTimeColumn() + ", " + Config.getCloudColumn() + ") VALUES ('" + p.getUniqueId().toString() + "', '0', '" + group + "');");
        }
    }
}

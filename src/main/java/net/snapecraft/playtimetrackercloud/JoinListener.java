package net.snapecraft.playtimetrackercloud;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        CloudApi.updateCloud(e.getPlayer());
        Api.joinList.put(e.getPlayer(), System.currentTimeMillis());
    }


}

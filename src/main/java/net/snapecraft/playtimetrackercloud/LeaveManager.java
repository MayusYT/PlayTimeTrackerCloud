package net.snapecraft.playtimetrackercloud;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveManager implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Long oldMillis = Api.joinList.get(e.getPlayer());
        Long playedMillis = System.currentTimeMillis() - oldMillis;

        Api.addPlaytime(e.getPlayer().getUniqueId(), playedMillis);
        Api.joinList.remove(e.getPlayer());
    }

}

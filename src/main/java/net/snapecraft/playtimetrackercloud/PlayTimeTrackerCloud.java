package net.snapecraft.playtimetrackercloud;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class PlayTimeTrackerCloud extends JavaPlugin {

    @Override
    public void onEnable() {
        init();
        // Plugin startup logic
        System.out.println("\n       _             _   _                _                  _             \n" +
                "      | |           | | (_)              | |                | |            \n" +
                " _ __ | | __ _ _   _| |_ _ _ __ ___   ___| |_ _ __ __ _  ___| | _____ _ __ \n" +
                "| '_ \\| |/ _` | | | | __| | '_ ` _ \\ / _ \\ __| '__/ _` |/ __| |/ / _ \\ '__|\n" +
                "| |_) | | (_| | |_| | |_| | | | | | |  __/ |_| | | (_| | (__|   <  __/ |   \n" +
                "| .__/|_|\\__,_|\\__, |\\__|_|_| |_| |_|\\___|\\__|_|  \\__,_|\\___|_|\\_\\___|_| w/ CLOUD-module\n" +
                "| |             __/ |                                                      \n" +
                "|_|            |___/                                                       ");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void init() {
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new LeaveManager(), this);

        Config.setDefaults();
        try {
            Config.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

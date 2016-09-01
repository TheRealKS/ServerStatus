package com.koens.serverstatus;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerStatus extends JavaPlugin {
    
    private int taskID;

    @Override
    public void onEnable() {
        getServer().getScheduler().runTaskAsynchronously(this, new UpdateUpStatus(1, this));
        saveDefaultConfig();
        int delay = getConfig().getInt("updater-interval");
        taskID = getServer().getScheduler().runTaskTimerAsynchronously(this, new Updater(this), delay * 20L, delay * 20L).getTaskId();
        getLogger().info("Server Status has been enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTask(taskID);
        getServer().getScheduler().runTaskAsynchronously(this, new UpdateUpStatus(0, this));
        getLogger().info("Server Status has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("statusupdate")) {
            if (sender.isOp())
            getServer().getScheduler().runTaskAsynchronously(this, new Updater(this));
            else
            sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
        }
        return true;
    }

    public int getPlayersOnline() {
        return getServer().getOnlinePlayers().size();
    }

    public String getWorldsString() {
        StringBuilder sb = new StringBuilder();
        for (World w : getServer().getWorlds()) {
            sb.append(w.getName() + ":" + w.getPlayers().size() + "||");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    public String getUrl() {
        return getConfig().getString("url");
    }
}

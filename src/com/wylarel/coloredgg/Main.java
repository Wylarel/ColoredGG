package com.wylarel.coloredgg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		saveDefaultConfig();
		System.out.println("ChatPing started");
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("ChatPing stopped");
	}
	
	@EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
		String trigger = getConfig().getString("trigger");
		if(!e.getMessage().toLowerCase().contains(trigger.toLowerCase()) || !e.getPlayer().hasPermission("coloredgg.use")) return;
		String lastColor = ChatColor.getLastColors(e.getMessage());
        e.setMessage(e.getMessage().replaceAll((getConfig().getBoolean("case_sensitive") ? "" : "(?i)") + trigger, getConfig().getString("format").replace("&", "§") + getConfig().getString("replace_by") + (lastColor.isEmpty() ? ChatColor.RESET : lastColor)));
    }
}
	
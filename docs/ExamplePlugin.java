package com.example.plugin;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import net.xtitle.api.AdaptManager;
import net.xtitle.api.TitleManager;
import net.xtitle.lib.XTitle;
import net.xtitle.adapt.v1_8_R3.AdaptHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin
extends JavaPlugin
implements CommandExecutor, TabCompleter, Listener {
	private final List<String> commandArgs = new ArrayList<>();
	
	private AdaptManager adaptManager;
	private TitleManager titleManager;
	
	@Override
	public void onLoad() {
		adaptManager = XTitle.newAdaptManager();
		adaptManager.adapt(new AdaptHandler());
		
		titleManager = XTitle.newTitleManager(adaptManager.getAdapt());
	}
	
	@Override
	public void onEnable() {
		getLogger().info("Enabled!");
		
		getServer().getPluginManager().registerEvents(this, this);
		
		getCommand("xtitle").setExecutor((sender, command, label, args) -> {
			if (!(sender instanceof Player)) return false;
			
			Player player = (Player) sender;
			
			if (args.length == 0) return false;
			
			if (args[0].equalsIgnoreCase("title")) {
				titleManager.sendTitle(player, "Hello!", "Title Example from command!");
				return false;
			}
			
			if (args[0].equalsIgnoreCase("cleartitle")) {
				titleManager.clearTitle(player);
				return false;
			}
			
			if (args[0].equalsIgnoreCase("actionbar")) {
				titleManager.sendActionBar(player, "ActionBar Example from command!");
				return false;
			}
			
			player.sendMessage("Unknown sub-command type!");
			return false;
		});
		
		getCommand("xtitle").setTabCompleter((sender, command, label, args) -> {
			if (commandArgs.isEmpty()) {
				commandArgs.add("title");
				commandArgs.add("cleartitle");
				commandArgs.add("actionbar");
			}
			
			List<String> results = new ArrayList<>();
			
			if (args.length == 1) {
				for (String result : results) {
					if (result.toLowerCase().startsWith(args[0].toLowerCase())) results.add(result);
				}
				
				return results;
			}
		});
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		titleManager.sendTitle(player, 20, 60, 20, "Hello!", "Title Example!");
		titleManager.sendActionBar(this, player, "ActionBar Example!", 100);
	}
	
	@Override
	public void onDisable() {
		if (titleManager != null) titleManager = null;
		
		if (adaptManager != null) adaptManager = null;
	}
}
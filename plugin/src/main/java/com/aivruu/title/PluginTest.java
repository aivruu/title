package com.aivruu.title;

import com.aivruu.title.adapt.v1_8_R3.AdaptHandler;
import com.aivruu.title.model.AdaptManager;
import com.aivruu.title.model.TitleManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginTest extends JavaPlugin {
	private TitleManager titleManager;
	
	@Override
	public void onLoad() {
		final AdaptManager adaptManager = Title.newAdaptManager();
		if (!adaptManager.of(new AdaptHandler())) return;
		
		titleManager = Title.newTitleManager(adaptManager.get());
	}
	
	@Override
	public void onEnable() {
		getCommand("test").setExecutor((sender, command, label, args) -> {
			if (!(sender instanceof Player)) return false;
			
			final Player player = (Player) sender;
			
			if (args.length == 0) return false;
			
			switch (args[0]) {
				default: return false;
				case "title":
					titleManager.showTitle(player, "Hello!", "This is an example title.");
					break;
				case "actionbar":
					titleManager.showActionBar(this, player, "ActionBar Example Message!", 5);
					break;
				case "tab":
					titleManager.setHeaderAndFooter(player, "Header Example", "Footer Example");
					break;
				case "header":
					titleManager.setHeader(player, "Header Test Example");
					break;
				case "footer":
					titleManager.setFooter(player, "Footer Test Example");
			}
			
			return false;
		});
	}
}

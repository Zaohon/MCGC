package cn.blockmc.Zao_hon.ServerChat;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import cn.blockmc.Zao_hon.ServerChat.Utils.BungeeUtil;
import cn.blockmc.Zao_hon.ServerChat.Utils.MessageListener;
import cn.blockmc.Zao_hon.ServerChat.Utils.MessageType;
import cn.blockmc.Zao_hon.ServerChat.command.BuyCommand;
import cn.blockmc.Zao_hon.ServerChat.command.CommandDispatcher;
import cn.blockmc.Zao_hon.ServerChat.command.GiveCommand;
import cn.blockmc.Zao_hon.ServerChat.command.IgnoreCommand;
import cn.blockmc.Zao_hon.ServerChat.command.ReloadCommand;
import cn.blockmc.Zao_hon.ServerChat.command.SendCommand;
import cn.blockmc.Zao_hon.ServerChat.command.SetItemCommand;
import cn.blockmc.Zao_hon.ServerChat.command.UpdateCommand;
import cn.blockmc.Zao_hon.ServerChat.configuration.Config;
import cn.blockmc.Zao_hon.ServerChat.configuration.Message;
import net.milkbowl.vault.economy.Economy;

public class ServerChat extends JavaPlugin implements Listener {
	public static final String PREFIX = ChatColor.GREEN + "[ServerChat]" + ChatColor.RESET;
//	private File itemFile;
	private Economy economy;
	private Message message;
//	private ItemStack horn = null;
	private HashMap<UUID, Boolean> ignored = new HashMap<UUID, Boolean>();
	private CommandDispatcher commandDispatcher;
	private Plugin placeholderAPI = null;
	private Updater updater = null;

	@Override
	public void onEnable() {
		Config.init(this);

		this.loadDepends();
		HornItem.init(this);
		BungeeUtil.init(this);
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new MessageListener(this));
		this.getServer().getPluginManager().registerEvents(new EventListener(this), this);

		this.message = new Message(this);
		message.setLanguage(Config.LANG);

		updater = new Updater(this);
		updater.hourlyUpdateCheck(getServer().getConsoleSender(), Config.AUTO_UPDATE_CHECK, false);

		commandDispatcher = new CommandDispatcher(this, "ServerChat", Message.getString("command_description_plugin"));
		this.getCommand("ServerChat").setExecutor(commandDispatcher);
		commandDispatcher.registerCommand(new SetItemCommand(this));
		commandDispatcher.registerCommand(new GiveCommand(this));
		commandDispatcher.registerCommand(new SendCommand(this));
		commandDispatcher.registerCommand(new IgnoreCommand(this));
		commandDispatcher.registerCommand(new BuyCommand(this));
		commandDispatcher.registerCommand(new UpdateCommand(this));
		commandDispatcher.registerCommand(new ReloadCommand(this));

		@SuppressWarnings("unused")
		Metrics metrics = new Metrics(this);

		PR("========================");
		PR("      ServerChat          ");
		PR("     Version: " + this.getDescription().getVersion());
		PR("     Author:Zao_hon           ");
		PR("========================");

	}

	@Override
	public void onDisable() {
		PR("ServerChat Disabled");
	}

	private void loadDepends() {
		if (setupEconomy()) {
			PR("�Ѽ��ؾ��ò��Vault");
		}

		if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
			PR("�Ѽ���ǰ�ò��PlaceholderAPI");
			placeholderAPI = getServer().getPluginManager().getPlugin("PlaceholderAPI");
		}
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		economy = rsp.getProvider();
		return economy != null;
	}

//	public boolean loadHorn() {
//		if (itemFile == null) {
//			itemFile = new File(this.getDataFolder(), "Item.yml");
//		}
//		if (!itemFile.exists()) {
//			this.setHorn(DefaultItem.getHorn());
//			return true;
//		}
//
//		horn = YamlConfiguration.loadConfiguration(itemFile).getItemStack("Item");
//		return true;
//	}

//	public ItemStack getHorn() {
//		return horn;
//	}

//	public boolean setHorn(ItemStack item) {
//		horn = item;
//		FileConfiguration config = YamlConfiguration.loadConfiguration(itemFile);
//		try {
//			config.set("Item", item);
//			config.save(itemFile);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

//	public void sendServerChat(String servername, String playername, String msg) {
//
//		if (Config.BOSS_BAR_ENABLE) {
//			String message = Config.BOSS_BAR_MESSAGE.replace("%message%", msg).replaceAll("%server%", servername)
//					.replaceAll("%player%", playername);
//
//			BarColor color = BarColor.valueOf(Config.BOSS_BAR_COLOR);
//			BarStyle style = BarStyle.valueOf(Config.BOSS_BAR_STYLE);
//			List<String> sflags = Config.BOSS_BAR_FLAGS;
//			BarFlag[] flags = new BarFlag[sflags.size()];
//			for (int i = 0; i < sflags.size(); i++) {
//				flags[i] = BarFlag.valueOf(sflags.get(i));
//			}
//			BossBar bar = Bukkit.createBossBar(shieldReplace(message), color, style, flags);
//
//			Bukkit.getServer().getOnlinePlayers().forEach(p -> {
//				if (!ignored.getOrDefault(p.getUniqueId(), false))
//					bar.addPlayer(p);
//			});
//			bar.setVisible(true);
//			Bukkit.getScheduler().runTaskLater(this, () -> bar.removeAll(),
//					getConfig().getInt("BossBarContinued") * 20);
//		}
//
//		if (Config.ACTION_BAR_ENABLE) {
//			String message = Config.ACTION_BAR_MESSAGE.replace("%message%", msg).replaceAll("%server%", servername)
//					.replaceAll("%player%", playername);
//			Bukkit.getOnlinePlayers().forEach(p -> {
//				if (!ignored.getOrDefault(p.getUniqueId(), false)) {
//					NMSUtils.sendActionBar(p, shieldReplace(message));
//				}
//			});
//		}
//
//		if (Config.AT_ENABLE) {
//			Pattern p = Pattern.compile("@(.+)\\s");
//			Matcher m = p.matcher(msg);
//			while (m.find()) {
//				String name = m.group(1);
//				Player target = Bukkit.getServer().getPlayerExact(name);
//				if (target != null && target.isOnline()) {
//					Message.playerSendMessage(target, Message.getString("at_tip", "%player%", playername));
//					target.playSound(target.getLocation(), Sound.valueOf(Config.AT_SOUND), 1, 1);
//					msg = msg.replace(m.group(0), "��b" + m.group(0) + "��r");
//				}
//
//			}
//		}
//
//		if (Config.CHAT_ENABLE) {
//			String message = Config.CHAT_MESSAGE.replace("%message%", msg).replaceAll("%server%", servername)
//					.replaceAll("%player%", playername);
//
//			Bukkit.getOnlinePlayers().forEach(p -> {
//				if (!ignored.getOrDefault(p.getUniqueId(), false))
//					p.sendMessage(shieldReplace(message));
//			});
//		}
//		PR("<" + servername + "> " + playername + " : " + msg);
//	}

	public void sendMsg(String message, MessageType type) {
		switch (type) {
		case CHAT:
			String msg = message;

			if (Config.AT_ENABLE) {
				Pattern p = Pattern.compile("@(.+)\\s");
				Matcher m = p.matcher(msg);
				while (m.find()) {
					String name = m.group(1);
					Player target = Bukkit.getServer().getPlayerExact(name);
					if (target != null && target.isOnline()) {
						// Message.playerSendMessage(target, Message.getString("at_tip", "%player%",
						// playername));
						target.playSound(target.getLocation(), Sound.valueOf(Config.AT_SOUND), 1, 1);
						msg = msg.replace(m.group(0), "��b" + m.group(0) + "��r");
					}

				}
			}
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (!ignored.getOrDefault(player.getUniqueId(), false))
					player.sendMessage(msg);
			}

			break;
		case BOSS_BAR:
			BarColor color = BarColor.valueOf(Config.BOSS_BAR_COLOR);
			BarStyle style = BarStyle.valueOf(Config.BOSS_BAR_STYLE);
			List<String> sflags = Config.BOSS_BAR_FLAGS;
			BarFlag[] flags = new BarFlag[sflags.size()];
			for (int i = 0; i < sflags.size(); i++) {
				flags[i] = BarFlag.valueOf(sflags.get(i));
			}
			BossBar bar = Bukkit.createBossBar(message, color, style, flags);
			Bukkit.getServer().getOnlinePlayers().forEach(p -> {
				if (!ignored.getOrDefault(p.getUniqueId(), false))
					bar.addPlayer(p);
			});
			bar.setVisible(true);
			Bukkit.getScheduler().runTaskLater(this, () -> bar.removeAll(),
					getConfig().getInt("BossBarContinued") * 20);
			break;
		case ACTION_BAR:
			Bukkit.getOnlinePlayers().forEach(p -> {
				if (!ignored.getOrDefault(p.getUniqueId(), false)) {
					NMSUtils.sendActionBar(p, message);
				}
			});
			break;
		default:
			return;
		}
	}

	public Updater getUpdater() {
		return updater;
	}

	public Economy getEconomy() {
		return economy;
	}

	public Plugin getPlaceholderAPI() {
		return placeholderAPI;
	}

	public Message getMessage() {
		return message;
	}

	public boolean isServerChatIgnored(UUID uuid) {
		return ignored.getOrDefault(uuid, false);
	}

	public boolean changePlayerIgnored(UUID uuid) {
		ignored.putIfAbsent(uuid, Boolean.FALSE);
		return !ignored.put(uuid, !ignored.get(uuid));
	}

	public void PR(String str) {
		this.getLogger().info(str);
	}

}

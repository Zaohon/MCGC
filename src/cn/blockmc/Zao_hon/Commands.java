package cn.blockmc.Zao_hon;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import cn.blockmc.Zao_hon.configuration.Config;
import cn.blockmc.Zao_hon.configuration.Lang;

public class Commands implements CommandExecutor {
	private ServerChat plugin;

	public Commands() {
		this.plugin = ServerChat.getInstance();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			Lang.sendMsg(sender, Lang.ONLY_PLAYER_USE_COMMAND);
			return true;
		}
		Player p = (Player) sender;
		int lenth = args.length;
		if (lenth >= 1) {
			String a1 = args[0];
			if (a1.equals("setitem")) {
				if (!p.hasPermission("ServerChat.Admin")) {
					Lang.sendMsg(p, Lang.NO_PERMISSION);
					return true;
				}
				ItemStack trumple = p.getInventory().getItemInMainHand();
				if (trumple == null || trumple.getType() == Material.AIR) {
					Lang.sendMsg(p, Lang.HORN_CANT_BE_AIR);
					return true;
				}
				plugin.setHorn(trumple);
				Lang.sendMsg(p, Lang.SUCCESS_SET_HORN);
				return true;
			} else if (a1.equals("give")) {
				if (!p.hasPermission("ServerChat.Admin")) {
					Lang.sendMsg(p, Lang.NO_PERMISSION);
					return true;
				}
				Player gp = lenth == 1 ? p : Bukkit.getPlayer(args[1]);
				int number = 1;
				try {
					number = Integer.valueOf(args[2]);
				} catch (Exception e) {
					// ignore
				}
				ItemStack horn = plugin.getHorn().clone();
				horn.setAmount(number);
				gp.getInventory().addItem(horn);
				if (gp != p) {
					Lang.sendMsg(p,
							Lang.GIVE_PLAYER_HORN.replace("%player%", p.getName()).replace("%number%", number + ""));
				}
				Lang.sendMsg(p, Lang.RECEIVE_HORN.replace("%number%", number + ""));
				return true;
			} else if (a1.equals("reload")) {
				if (!p.hasPermission("ServerChat.Admin")) {
					Lang.sendMsg(p, Lang.NO_PERMISSION);
					return true;
				}
				Config.reload();
				Lang.reload();
				// plugin.Message.load();
				// plugin.loadHorn();
				Lang.sendMsg(p, Lang.COMMAND_RELOAD_COMPLETELY);
				return true;
			} else if (a1.equals("ignore")) {
				if (!p.hasPermission("ServerChat.Ignore")) {
					Lang.sendMsg(p, Lang.NO_PERMISSION_IGNORE);
					return true;
				}
				if (!plugin.changePlayerIgnored(p.getUniqueId())) {
					Lang.sendMsg(p, Lang.IGNORE_SERVERCHAT_ON);
				} else {
					Lang.sendMsg(p, Lang.IGNORE_SERVERCHAT_OFF);
				}
				return true;
			} else if (Config.COST_ENABLE && a1.equals("buy")) {
				if (!p.hasPermission("ServerChat.Buy")) {
					Lang.sendMsg(p, Lang.NO_PERMISSION_BUY);
					return true;
				}
				int number = 1;
				try {
					number = Integer.valueOf(args[1]);
				} catch (Exception e) {
					// ignore
				}
				int mc = Config.COST_MONEY;
				double mn = plugin.getEconomy() == null ? -1 : plugin.getEconomy().getBalance(p);
				if (mc != 0 && mn >= mc) {
					plugin.getEconomy().depositPlayer(p, mc);
					Lang.sendMsg(p, Lang.COMMAND_BUY_COST_MONEY.replace("%number%", number + "").replace("%money%",
							mc * number + ""));
				} else {
					Lang.sendMsg(p, Lang.COMMAND_BUY_COST_FAILED);
				}
				return true;
			}
		}
		Lang.sendMsg(p, Lang.COMMAND_HEADING);
		Lang.sendMsg(p, Lang.COMMAND_SETITEM);
		Lang.sendMsg(p, Lang.COMMAND_GIVEPLAYER);
		Lang.sendMsg(p, Lang.COMMAND_IGNORED);
		Lang.sendMsg(p, Lang.COMMAND_RELOAD);
		if (Config.COST_ENABLE)
			Lang.sendMsg(p, Lang.COMMAND_BUY);
		return true;
	}

}

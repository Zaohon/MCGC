package cn.blockmc.ServerChat.Zao_hon;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {
	private ServerChat plugin;

	public Commands(ServerChat plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("��2�ò��ָ��ֻ�����ʹ��");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("ServerChat.Admin")) {
			p.sendMessage("��cȨ�޲���");
			return true;
		}
		if (args.length >= 1) {
			String a1 = args[0];
			if (a1.equals("setitem")) {
				ItemStack trumple = p.getInventory().getItemInMainHand();
				if (trumple == null || trumple.getType() == Material.AIR) {
					p.sendMessage("��c���Ȳ���Ϊ������");
					return true;
				}
				plugin.getConfig().set("Item.Material", trumple.getType().name());
				plugin.getConfig().set("Item.Name", trumple.getItemMeta().getDisplayName());
				plugin.getConfig().set("Item.Lore", trumple.getItemMeta().getLore());
				plugin.saveConfig();
				plugin.reloadConfig();
				plugin.updateTrumpleItem();
				p.sendMessage("��b���ÿ�����ȳɹ�");
				return true;
			} else if (a1.equals("give")) {
				Player gp = args.length == 1 ? p : Bukkit.getPlayer(args[1]);
				gp.getInventory().addItem(plugin.getHorn().clone());
				if (gp != p) {
					p.sendMessage("��b�Ѹ�����ҡ�a" + gp.getDisplayName() + "��bһ���������");
				}
				gp.sendMessage("��b������һ���������");
				return true;
			} else if (a1.equals("reload")) {
				plugin.reloadConfig();
				plugin.updateTrumpleItem();
				p.sendMessage("��2ServerChat�������");
				return true;
			}
		}
		p.sendMessage("��2---ServerChat---");
		p.sendMessage("��2/sc setitem   ��6--���������ŵ���Ʒ����Ϊ����");
		p.sendMessage("��2/sc give [���]   ��6--�������һ������,��������Լ�");
		p.sendMessage("��2/sc reload   ��6--���ز������");

		return true;
	}

}

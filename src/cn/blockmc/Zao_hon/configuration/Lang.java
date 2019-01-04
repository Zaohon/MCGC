package cn.blockmc.Zao_hon.configuration;

import java.io.File;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import cn.blockmc.Zao_hon.ServerChat;

public class Lang {
	private static ServerChat plugin;
	public static String COMMAND_HEADING;
	public static String COMMAND_SETITEM;
	public static String COMMAND_GIVEPLAYER;
	public static String COMMAND_IGNORED;
	public static String COMMAND_BUY;
	public static String COMMAND_BUY_DENY;
	public static String COMMAND_BUY_COST_MONEY;
	public static String COMMAND_BUY_COST_POINT;
	public static String COMMAND_BUY_COST_FAILED;
	public static String COMMAND_RELOAD;
	public static String COMMAND_RELOAD_COMPLETELY;
	public static String CHAT_LENTH_ERROR;
	public static String CHAT_MSG_EMPTY;
	public static String IGNORE_SERVERCHAT_ON;
	public static String IGNORE_SERVERCHAT_OFF;
//	public static String RELOAD_COMPLETELY;
	public static String RECEIVE_HORN;
	public static String GIVE_PLAYER_HORN;
	public static String SUCCESS_SET_HORN;
	public static String HORN_CANT_BE_AIR;
	public static String NO_PERMISSION_BUY;
	public static String NO_PERMISSION_IGNORE;
	public static String NO_PERMISSION;
	public static String ONLY_PLAYER_USE_COMMAND;
	public static String WITHOUT_AUTHENTICATED;
	public static String CHAT_IN_COOL_TIME;
	public static String HORN_IN_COOL_TIME;
	public static String HINT_OVERTIME_USEHORN;
	public static String HINT_WHEN_USING_HORN;
	public static String ALREADY_USING_HORN;
	public static String AUTO_COST_MONEY;
	public static String AUTO_COST_POINT;
	public static String AUTO_COST_FAILED_MONEY;
	public static String AUTO_COST_FAILED_POINT;
	public static String AUTO_USE_SUCCESS;
	public static String AUTO_USE_FAILED;

	public static void reload() {
		plugin = ServerChat.getInstance();
		String langfile = plugin.getConfig().getString("Lang") + ".yml";
		File langFile = new File(plugin.getDataFolder(), langfile);
		if (!langFile.exists()) {
			plugin.saveResource(langfile, false);
		}
		FileConfiguration config = YamlConfiguration.loadConfiguration(langFile);

		COMMAND_HEADING = config.getString("Command_heading", "��2---ServerChat---");
		COMMAND_SETITEM = config.getString("Command_setitem", "��2/sc setitem   ��6--���������ŵ���Ʒ����Ϊ����");
		COMMAND_GIVEPLAYER = config.getString("Command_giveplayer", "��2/sc give [���]   ��6--�������һ������,��������Լ�");
		COMMAND_IGNORED = config.getString("Command_ignored", "��2/sc ignore --��6�������п����Ϣ");
		COMMAND_BUY = config.getString("Command_buy","/sc buy [����] --����������");
		COMMAND_BUY_COST_MONEY = config.getString("Command_buy_cost_money","��a�ɹ������b%number%��a���������,���ѡ�e%money%��a���");
		COMMAND_BUY_COST_POINT = config.getString("Command_buy_cost_point","��a�ɹ������b%number%��a���������,���ѡ�d%money%��a��ȯ");
		COMMAND_BUY_COST_FAILED = config.getString("Command_buy_cost_failed","��c����ʧ��,Ǯ����");
		COMMAND_RELOAD = config.getString("Command_reload: ", "��2/sc reload   ��6--���ز������");
		CHAT_LENTH_ERROR = config.getString("ChatLenthError", "��c��˵�Ļ�̫������̫����");
		CHAT_MSG_EMPTY = config.getString("ChatMsgEmpty", "��c��Ϣ����Ϊ��");
		IGNORE_SERVERCHAT_ON = config.getString("Ignore_ServerChat_On", "��e�������п����Ϣ");
		IGNORE_SERVERCHAT_OFF = config.getString("Ignore_ServerChat_Off", "��e�������п����Ϣ");
		COMMAND_RELOAD_COMPLETELY = config.getString("Command_reload_completely", "��2ServerChat�������");
		RECEIVE_HORN = config.getString("ReceiveAHorn", "��b���յ��ˡ�e%number%��b���������");
		GIVE_PLAYER_HORN = config.getString("GivePlayerHorn", "��b�Ѹ�����ҡ�a%player%��e%number%��b�������");
		SUCCESS_SET_HORN = config.getString("SuccessSetHornItem", "��b���ÿ�����ȳɹ�");
		HORN_CANT_BE_AIR = config.getString("HornCantBeAir", " ��c���Ȳ���Ϊ������");
		NO_PERMISSION_BUY = config.getString("NoPermissionBuy","��c��û��Ȩ�޹���������");
		NO_PERMISSION_IGNORE = config.getString("NoPermissionIgnore", "��cȱ�����ο�������Ȩ��");
		NO_PERMISSION = config.getString("NoPermission", "��cȨ�޲���");
		ONLY_PLAYER_USE_COMMAND = config.getString("OnlyPlayerUseCommand", "��2��ָ��ֻ�����ʹ��");
		WITHOUT_AUTHENTICATED = config.getString("WithoutAuthenticated", "��c���ȵ�¼����ʹ�ÿ������");
		CHAT_IN_COOL_TIME = config.getString("ChatInCoolTime", "��c�㻹Ҫ�ٵȡ�e%cooltime%��c������ٴη��Ϳ����Ϣ");
		HORN_IN_COOL_TIME = config.getString("HornInCoolTime", "��c�㻹Ҫ�ٵȡ�e%cooltime%��c������ٴ�ʹ�ÿ������");
		HINT_OVERTIME_USEHORN = config.getString("Hint_OvertimeUseHorn", "��a���ڳ�ʱ��û��ʹ�ã�����������Զ��˻�");
		HINT_WHEN_USING_HORN = config.getString("Hint_WhenUsingHorn", "��d��������Ҫ���͵���������");
		ALREADY_USING_HORN = config.getString("AlreadyUsingHorn", "��6���Ѿ���һ������ʹ�õĿ��������");
		AUTO_COST_MONEY = config.getString("Auto_Use_Money", "��a���Զ�����������,���ѡ�e%money%��a��Ǯ");
		AUTO_COST_POINT = config.getString("Auto_Cost_Point", "��a���Զ�����������,���ѡ�d%point%��ȯ");
		AUTO_COST_FAILED_MONEY = config.getString("Auto_Cost_Failed_Money", "��c����Ҫ��e%money%��c��Ҳ��ܿ����Ϣ");
		AUTO_COST_FAILED_POINT = config.getString("Auto_Cost_Failed_Point", "��c����Ҫ��e%point%��c��ȯ���ܷ��Ϳ����Ϣ");
		AUTO_USE_SUCCESS = config.getString("Auto_USE_HORN", "��a�����ɹ�,����һ���������");
		AUTO_USE_FAILED = config.getString("Auto_Use_Failed", "��cȱ�ٿ������");

	}
	public static void sendMsg(CommandSender sender,String msg){
		if(msg!=null&&!msg.equals("")){
			if(sender instanceof Player){
				msg = msg.replaceAll("&", "��");
			}
			sender.sendMessage(msg);
		}
	}

}

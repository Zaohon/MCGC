package configuration;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import cn.blockmc.Zao_hon.ServerChat;

public class Lang {
	private static ServerChat plugin;
	public static String COMMAND_HEADING;
	public static String COMMAND_SETITEM; 
	public static String COMMAND_GIVEPLAYER; 
	public static String COMMAND_IGNORED;
	public static String COMMAND_RELOAD;
	public static String CHAT_LENTH_ERROR;
	public static String IGNORE_SERVERCHAT_ON ;
	public static String IGNORE_SERVERCHAT_OFF;
	public static String RELOADCOMPLETELY;
	public static String RECEIVEHORN;
	public static String GIVEPLAYERHORN;
	public static String SUCCESSSETHORN;
	public static String HORNCANTBEAIR;
	public static String NOPERMISSIONIGNORE;
	public static String NOPERMISSION;
	public static String ONLYPLAYERUSECOMMAND;
	public static String WITHOUTAUTHENTICATED;
	public static String INCOOLTIME;
	public static String HINT_OVERTIMEUSEHORN;
	public static String HINT_WHENUSINGHORN;
	public static String ALREADYUSINGHORN;
	public static String AUTO_COST_MONEY;
	public static String AUTO_COST_POINT;
	public static String AUTO_COST_FAILED;
	public static String AUTO_USE_SUCCESS;
	public static String AUTO_USE_FAILED;
	public static void reload(){
		plugin = ServerChat.getInstance();
		String langfile = plugin.getConfig().getString("Lang")+".yml";
		File langFile = new File(plugin.getDataFolder(),langfile);
		if(!langFile.exists()){
			plugin.saveResource(langfile, false);
		}
		FileConfiguration config = YamlConfiguration.loadConfiguration(langFile);
		
		COMMAND_HEADING = config.getString("Command_heading","��2---ServerChat---");
		COMMAND_SETITEM = config.getString("Command_setitem","��2/sc setitem   ��6--���������ŵ���Ʒ����Ϊ����");
		COMMAND_GIVEPLAYER = config.getString("Command_giveplayer","��2/sc give [���]   ��6--�������һ������,��������Լ�");
		COMMAND_IGNORED = config.getString("Command_ignored","��2/sc ignore --��6�������п����Ϣ");
		COMMAND_RELOAD = config.getString("Command_reload: ","��2/sc reload   ��6--���ز������");
		IGNORE_SERVERCHAT_ON = config.getString("Ignore_ServerChat_On","��e�������п����Ϣ");
		IGNORE_SERVERCHAT_OFF = config.getString("Ignore_ServerChat_Off","��e�������п����Ϣ");
		RELOADCOMPLETELY = config.getString("ReloadCompletely","��2ServerChat�������");
		RECEIVEHORN= config.getString("ReceiveAHorn","��b���յ��ˡ�e%number%��b���������");
		GIVEPLAYERHORN = config.getString("GivePlayerHorn","��b�Ѹ�����ҡ�a%player%��e%number%��b�������");
		SUCCESSSETHORN = config.getString("SuccessSetHornItem","��b���ÿ�����ȳɹ�");
		HORNCANTBEAIR = config.getString("HornCantBeAir"," ��c���Ȳ���Ϊ������");
		NOPERMISSIONIGNORE=config.getString("NoPermissionIgnore","��cȱ�����ο�������Ȩ��");
		NOPERMISSION = config.getString("NoPermission","��cȱ�ݲ���");
		ONLYPLAYERUSECOMMAND = config.getString("OnlyPlayerUseCommand","��2��ָ��ֻ�����ʹ��");
		WITHOUTAUTHENTICATED = config.getString("WithoutAuthenticated","��c���ȵ�¼����ʹ�ÿ������");
		INCOOLTIME = config.getString("InCoolTime","��c�㻹Ҫ�ٵȡ�e%cooltime%��c������ٴη��Ϳ������");
		HINT_OVERTIMEUSEHORN = config.getString("Hint_OvertimeUseHorn","��a���ڳ�ʱ��û��ʹ�ã�����������Զ��˻�");
		HINT_WHENUSINGHORN = config.getString("HintWhenUsingHorn"," ��d��������Ҫ���͵���������");
		ALREADYUSINGHORN = config.getString("AlreadyUsingHorn","��6���Ѿ���һ������ʹ�õĿ��������");
		AUTO_COST_MONEY = config.getString("Auto_Use_Money","��a���Զ�����������,���ѡ�e%money%��a��Ǯ");
		AUTO_COST_POINT = config.getString("Auto_Cost_Point","��a���Զ�����������,���ѡ�d%point%��ȯ");
		AUTO_COST_FAILED = config.getString("Auto_Cost_Failed","��cȱ�ٿ������");
		AUTO_USE_SUCCESS  = config.getString("Auto_USE_HORN","��a�����ɹ�,����һ���������");
		AUTO_USE_FAILED = config.getString("Auto_Use_Failed","��cȱ�ٿ������");
		
		
		
	}
	
}

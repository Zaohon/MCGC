package cn.blockmc.Zao_hon.Bukkit;

import java.io.File;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Message {
	private File msgfile = null;
	private ServerChat plugin;
	public String Command_heading = "��2---ServerChat---";
	public String Command_setitem = "��2/sc setitem   ��6--���������ŵ���Ʒ����Ϊ����";
	public String Command_giveplayer = "��2/sc give [���]   ��6--�������һ������,��������Լ�";
	public String Command_reload = "��2/sc reload   ��6--���ز������";
	public String ReloadCompletely = "��2ServerChat�������";
	public String ReceiveAHorn = "��b������һ���������";
	public String GivePlayerHorn = "��b�Ѹ�����ҡ�a%player%��bһ���������";
	public String SuccessSetHornItem = "��b���ÿ�����ȳɹ�";
	public String HornCantBeAir = "��c���Ȳ���Ϊ������";
	public String NoPermission = "��cȨ�޲���";
	public String OnlyPlayerUseCommand = "��2�ò��ָ��ֻ�����ʹ��";
	public String WithoutAuthenticated = "��c���ȵ�¼����ʹ�ÿ������";
	public String PrefixChatInCoolTime = "��c�㻹Ҫ�ٵȡ�e%cooltime%��c������ٴη��Ϳ������";
	public String HintOvertimeUseHorn = "��a���ڳ�ʱ��û��ʹ�ã�����������Զ��˻�";
	public String HintWhenUsingHorn = "��d��������Ҫ���͵���������";
	public String AlreadyUsingHorn = "��6���Ѿ���һ������ʹ�õĿ��������";

	public Message(ServerChat plugin) {
		this.plugin = plugin;
	}

	public void load() {
		if(msgfile==null){
			msgfile = new File(plugin.getDataFolder(),"Message.yml");
		}
		if(!msgfile.exists()){
			plugin.saveResource("Message.yml", true);
		}
		Configuration config = YamlConfiguration.loadConfiguration(msgfile);
		Command_heading = config.getString("Command-heading");
		Command_setitem = config.getString("Command-setitem");
		Command_giveplayer = config.getString("Command-giveplayer");
		Command_reload = config.getString("Command-reload");
		ReloadCompletely = config.getString("ReloadCompletely");
		ReceiveAHorn = config.getString("ReceiveAHorn");
		GivePlayerHorn = config.getString("GivePlayerHorn");
		SuccessSetHornItem = config.getString("SuccessSetHornItem");
		HornCantBeAir = config.getString("HornCantBeAir");
		NoPermission = config.getString("NoPermission");
		OnlyPlayerUseCommand = config.getString("OnlyPlayerUseCommand");
		WithoutAuthenticated = config.getString("WithoutAuthenticated");
		PrefixChatInCoolTime = config.getString("PrefixChatInCoolTime");
		HintOvertimeUseHorn = config.getString("HintOvertimeUseHorn");
		HintWhenUsingHorn = config.getString("HintWhenUsingHorn");
		AlreadyUsingHorn = config.getString("AlreadyUsingHorn");
	}

}

package cn.blockmc.Zao_hon;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Message {
	private File msgfile = null;
	private ServerChat plugin;
	public String Command_heading = "��2---ServerChat---";
	public String Command_setitem = "��2/sc setitem   ��6--���������ŵ���Ʒ����Ϊ����";
	public String Command_giveplayer = "��2/sc give [���]   ��6--�������һ������,��������Լ�";
	public String Command_ignored = "��2/sc ignore --��6�������п����Ϣ";
	public String Command_reload = "��2/sc reload   ��6--���ز������";
	public String Chat_Lenth_Error = "��c��˵�Ļ�̫������̫����";
	public String IgnoredServerChat_On = "��e�������п����Ϣ";
	public String IgnoredServerChat_Off = "��e�������п����Ϣ";
	public String ReloadCompletely = "��2ServerChat�������";
	public String ReceiveAHorn = "��b������һ���������";
	public String GivePlayerHorn = "��b�Ѹ�����ҡ�a%player%��bһ���������";
	public String SuccessSetHornItem = "��b���ÿ�����ȳɹ�";
	public String HornCantBeAir = "��c���Ȳ���Ϊ������";
	public String NoPermissionIgnore = "��c�㻹û��Ȩ�޺��Կ����Ϣ";
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
		if (msgfile == null) {
			msgfile = new File(plugin.getDataFolder(), "Message.yml");
		}
		if (!msgfile.exists()) {
			plugin.PR("û���ҵ������ļ�..");
			plugin.PR("���ڴ����µ�..");
			this.saveToFile();
			return;

		}
		Configuration config = YamlConfiguration.loadConfiguration(msgfile);
		Field[] msgfields = this.getClass().getFields();
		boolean update = false;
		for (int i = 0; i < msgfields.length; i++) {
			Field f = msgfields[i];
			if (f.getName().equals("msgfile") || f.getName().equals("plugin")) {
				continue;
			}
			try {
				String msg = config.getString(f.getName());
				if (msg == null) {
					plugin.PR("���������ļ������������޸�" + f.getName());
					update = true;
				} else {
					f.set(this, config.getString(f.getName()));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if(update){
			this.saveToFile();
		}
		//
		// Command_heading = config.getString("Command_heading");
		// Command_setitem = config.getString("Command_setitem");
		// Command_giveplayer = config.getString("Command_giveplayer");
		// Command_reload = config.getString("Command_reload");
		// ReloadCompletely = config.getString("ReloadCompletely");
		// ReceiveAHorn = config.getString("ReceiveAHorn");
		// GivePlayerHorn = config.getString("GivePlayerHorn");
		// SuccessSetHornItem = config.getString("SuccessSetHornItem");
		// HornCantBeAir = config.getString("HornCantBeAir");
		// NoPermission = config.getString("NoPermission");
		// OnlyPlayerUseCommand = config.getString("OnlyPlayerUseCommand");
		// WithoutAuthenticated = config.getString("WithoutAuthenticated");
		// PrefixChatInCoolTime = config.getString("PrefixChatInCoolTime");
		// HintOvertimeUseHorn = config.getString("HintOvertimeUseHorn");
		// HintWhenUsingHorn = config.getString("HintWhenUsingHorn");
		// AlreadyUsingHorn = config.getString("AlreadyUsingHorn");
	}
	private void saveToFile(){
		FileConfiguration config = YamlConfiguration.loadConfiguration(msgfile);
		Field[] msgfields = this.getClass().getFields();
		for (int i = 0; i < msgfields.length; i++) {
			Field f = msgfields[i];
			if (f.getName().equals("msgfile") || f.getName().equals("plugin")) {
				continue;
			}
			try {
				config.set(f.getName(), f.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		try {
			config.save(msgfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

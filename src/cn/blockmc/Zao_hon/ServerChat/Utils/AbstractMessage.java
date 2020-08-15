package cn.blockmc.Zao_hon.ServerChat.Utils;

public abstract class AbstractMessage {
	protected MessageType type;
	protected String sender;
	protected AbstractMessage(MessageType type,String sender) {
		this.type = type;
		this.sender = sender;
	}
	public abstract Byte[] toByte();
	public abstract AbstractMessage fromByte(Byte[] bytes);
}

package com.wisn.pm.bean;

/**
  * 
  * @author Wisn
  * 2016年9月30日   上午9:23:05
  *
  */
public class OperationMessage {
	/**
	 * 需要发送给用户的id
	 */
	private long messageSendToID;
	
	/**
	 * 发送者id 系统发送默认为0
	 */
	private long messageFromID=0;
	/**
	 * shutdown connection 
	 */
	private   int  operationType;
	
	/**
	 * 	 send message
	 *   appInstall 
	 *   lock  
	 *   reboot 
	 *   ring  
	 *   wipe data
	 *   change lockpassword
	 *   
	 */
	private  int messageType;
	
	/**
	 * send Time
	 */
	private  long  sendTime;
	
	/**
	 * commend  time
	 */
	private  long   executeTime;
	
	/**
	 * context
	 */
	private  String messageContext;
	
	/**
	 * 执行状态
	 */
	private  int  executeCode=DataCode.operation_sending;
	
	/**
	 * 消息内容
	 */
	private String   content;
	public OperationMessage() {
		super();
	}

	public OperationMessage(long messageSendToID, long messageFromID,
			int operationType, int messageType, long sendTime,
			long executeTime, String messageContext, int executeCode) {
		super();
		this.messageSendToID = messageSendToID;
		this.messageFromID = messageFromID;
		this.operationType = operationType;
		this.messageType = messageType;
		this.sendTime = sendTime;
		this.executeTime = executeTime;
		this.messageContext = messageContext;
		this.executeCode = executeCode;
	}

	public OperationMessage(int operationType, int messageType, long sendTime,
			long executeTime, String messageContext) {
		super();
		this.operationType = operationType;
		this.messageType = messageType;
		this.sendTime = sendTime;
		this.executeTime = executeTime;
		this.messageContext = messageContext;
	}

	public long getMessageSendToID() {
		return messageSendToID;
	}

	public void setMessageSendToID(long messageSendToID) {
		this.messageSendToID = messageSendToID;
	}

	public long getMessageFromID() {
		return messageFromID;
	}

	public void setMessageFromID(long messageFromID) {
		this.messageFromID = messageFromID;
	}

	public int getExecuteCode() {
		return executeCode;
	}

	public void setExecuteCode(int executeCode) {
		this.executeCode = executeCode;
	}

	public int getOperationType() {
		return operationType;
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	public long getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(long executeTime) {
		this.executeTime = executeTime;
	}

	public String getMessageContext() {
		return messageContext;
	}

	public void setMessageContext(String messageContext) {
		this.messageContext = messageContext;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "OperationMessage [messageSendToID=" + messageSendToID
				+ ", messageFromID=" + messageFromID + ", operationType="
				+ operationType + ", messageType=" + messageType
				+ ", sendTime=" + sendTime + ", executeTime=" + executeTime
				+ ", messageContext=" + messageContext + ", executeCode="
				+ executeCode + ", content=" + content + "]";
	}
 
}

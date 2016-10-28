package com.wisn.pmlib.bean;
/**
 * 
 * @author Wisn
 * 2016年9月30日   下午4:25:12
 *
 */
public class DeviceInformation {
	private 	long  id;
	private  String  userName;
	private  String passWord;
	private  String netWorkName;
	private  String deviceModel;
	private  String deviceManufacturer;//设备制造商
	private  String osVersion;
	private  String sdkVersion;
	private  String deviceName;
	private  String deviceId;
	private  String imsiNumber;
	private  String macAddress;
	private  int  isRooted;
	private  String SimSerialNumber;
	private  String AllSensorsName;//分号分隔
	
	public DeviceInformation() {
		super();
	}
	public DeviceInformation(String userName, String passWord, String netWorkName,
			String deviceModel, String deviceManufacturer, String osVersion,
			String sdkVersion, String deviceName, String deviceId,
			String imsiNumber, String macAddress, int isRooted,
			String simSerialNumber, String allSensorsName) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.netWorkName = netWorkName;
		this.deviceModel = deviceModel;
		this.deviceManufacturer = deviceManufacturer;
		this.osVersion = osVersion;
		this.sdkVersion = sdkVersion;
		this.deviceName = deviceName;
		this.deviceId = deviceId;
		this.imsiNumber = imsiNumber;
		this.macAddress = macAddress;
		this.isRooted = isRooted;
		SimSerialNumber = simSerialNumber;
		AllSensorsName = allSensorsName;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getNetWorkName() {
		return netWorkName;
	}
	public void setNetWorkName(String netWorkName) {
		this.netWorkName = netWorkName;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceManufacturer() {
		return deviceManufacturer;
	}
	public void setDeviceManufacturer(String deviceManufacturer) {
		this.deviceManufacturer = deviceManufacturer;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getSdkVersion() {
		return sdkVersion;
	}
	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getImsiNumber() {
		return imsiNumber;
	}
	public void setImsiNumber(String imsiNumber) {
		this.imsiNumber = imsiNumber;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public int getIsRooted() {
		return isRooted;
	}
	public void setIsRooted(int isRooted) {
		this.isRooted = isRooted;
	}
	public String getSimSerialNumber() {
		return SimSerialNumber;
	}
	public void setSimSerialNumber(String simSerialNumber) {
		SimSerialNumber = simSerialNumber;
	}
	public String getAllSensorsName() {
		return AllSensorsName;
	}
	public void setAllSensorsName(String allSensorsName) {
		AllSensorsName = allSensorsName;
	}
	@Override
	public String toString() {
		return "LoginParameter [userName=" + userName + ", passWord="
				+ passWord + ", netWorkName=" + netWorkName + ", deviceModel="
				+ deviceModel + ", deviceManufacturer=" + deviceManufacturer
				+ ", osVersion=" + osVersion + ", sdkVersion=" + sdkVersion
				+ ", deviceName=" + deviceName + ", deviceId=" + deviceId
				+ ", imsiNumber=" + imsiNumber + ", macAddress=" + macAddress
				+ ", isRooted=" + isRooted + ", SimSerialNumber="
				+ SimSerialNumber + ", AllSensorsName=" + AllSensorsName + "]";
	}
	
	

}

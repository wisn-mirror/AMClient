/*
 * Copyright (c) 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 * 
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.wisn.pmlib.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import com.wisn.pmlib.R;

import java.util.List;

/**
 * This class represents all the device information related APIs.
 */
public class DeviceInfo {
	private Root rootChecker;
	private Context context;
	private Resources resources;
	private TelephonyManager telephonyManager;

	public DeviceInfo(Context context) {
		this.context = context;
		this.resources = context.getResources();
		this.telephonyManager = (TelephonyManager) context.
									getSystemService(Context.TELEPHONY_SERVICE);
	}

	/**
	 * Returns the network operator name.
	 * @return - Network operator name.
	 */
	public String getNetworkOperatorName() {
		return telephonyManager.getSimOperatorName();
	}

	/**
	 * Returns the device model.
	 * @return - Device model.
	 */
	public String getDeviceModel() {
		return Build.MODEL;
	}

	/**
	 * Returns the device manufacturer.
	 * @return - Device manufacturer.
	 */
	public String getDeviceManufacturer() {
		return Build.MANUFACTURER;
	}

	/**
	 * Returns the OS version.
	 * @return - Device OS version.
	 */
	public String getOsVersion() {
		return Build.VERSION.RELEASE;
	}
    /**
     * 版本名
     */

    public  String getVersionName() {
        return getPackageInfo().versionName;
    }

    /**
     * 版本号
     */
    public  int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    private  PackageInfo getPackageInfo() {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                                   PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }
	/**
	 * Returns the SDK Version number.
	 * @return - Device android SDK version number.
	 */
	public int getSdkVersion() {
		return Build.VERSION.SDK_INT;
	}

	/**
	 * Returns the device name.
	 * @return - Device name.
	 */
	public String getDeviceName() {
		return Build.DEVICE;
	}

	/**
	 * Returns the IMEI Number.
	 * @return - Device IMEI number.
	 */
	public String getDeviceId() {
		String deviceId = telephonyManager.getDeviceId();
		
		if (deviceId == null || deviceId.isEmpty()) {
			deviceId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		}
		
		return deviceId;
	}

	/**
	 * Returns the IMSI Number.
	 * @return - Device IMSI number.
	 */
	public String getIMSINumber() {
		return telephonyManager.getSubscriberId();
	}

	/**
	 * Returns the device WiFi MAC.
	 * @return - Device WiFi MAC.
	 */
	public String getMACAddress() {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();
		return wInfo.getMacAddress();
	}

	/**
	 * Returns the Email address of the device owner.
	 * @return - Device owner email address.
	 */
	public String getEmail() {
		return Preference.getString(context, 
	                                    resources.getString(R.string.shared_pref_username));
	}

	/**
	 * Returns true if the device is a Rooted device.
	 * @return - Device rooted status.
	 */
	public boolean isRooted() {
		rootChecker = new Root();
		return rootChecker.isDeviceRooted();
	}

	/**
	 * Returns the SIM serial number.
	 * @return - Device SIM serial number.
	 */
	public String getSimSerialNumber() {
		return telephonyManager.getSimSerialNumber();
	}

	/**
	 * Returns all the sensors available on the device as a List.
	 * @return - List of all the sensors available on the device.
	 */
	public List<Sensor> getAllSensors() {
		SensorManager sensorManager =
                              (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		
		return sensorManager.getSensorList(Sensor.TYPE_ALL);
	}

}

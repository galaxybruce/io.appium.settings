/*
  Copyright 2012-present Appium Committers
  <p>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p>
  http://www.apache.org/licenses/LICENSE-2.0
  <p>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package io.appium.settings.custom.handlers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.thanosfisherman.wifiutils.WifiUtils;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener;

import io.appium.settings.handlers.AbstractSettingHandler;

public class WifiSwitchSettingHandler extends AbstractSettingHandler {

    public WifiSwitchSettingHandler(Context context) {
        super(context, "android.permission.ACCESS_WIFI_STATE",
                "android.permission.CHANGE_WIFI_STATE",
                "android.permission.ACCESS_COARSE_LOCATION",
                "android.permission.ACCESS_FINE_LOCATION");
    }

    @Override
    protected boolean setState(boolean state) {
        return true;
    }

    @Override
    public boolean _handleOtherData(Context context, Intent intent, String data) {
       String[] array = data.split(":");
       if(array.length < 2) {
           return false;
       }

       String ssid = array[0];
       String pwd = array[1];

       WifiUtils.withContext(context.getApplicationContext())
                .connectWith(ssid, pwd)
                .onConnectionResult(new ConnectionSuccessListener() {
                    @Override
                    public void isSuccessful(boolean isSuccess) {
                        Toast.makeText(context, isSuccess ? "连接成功" : "连接失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .start();
       return true;
    }

    @Override
    protected String getSettingDescription() {
        return "Vibrate";
    }
}

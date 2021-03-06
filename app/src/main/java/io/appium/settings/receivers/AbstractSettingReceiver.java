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

package io.appium.settings.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import io.appium.settings.handlers.AbstractSettingHandler;

public abstract class AbstractSettingReceiver extends BroadcastReceiver {
    private static final String TAG = AbstractSettingReceiver.class.getSimpleName();

    private static final String COMMAND = "setstatus";
    private static final String COMMAND_KEY_DATA = "setData";
    private static final String COMMAND_ENABLE = "enable";
    private static final String COMMAND_DISABLE = "disable";


    // am broadcast -a io.appium.settings.[wifi|data_connection|animation] --es setstatus [enable|disable]
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getExtras() == null) {
            setResultCode(Activity.RESULT_CANCELED);
            return;
        }
        if(intent.getExtras().containsKey(COMMAND)) {
            String command = intent.getStringExtra(COMMAND);
            List<String> supportedCommands = Arrays.asList(COMMAND_ENABLE, COMMAND_DISABLE);
            if (!supportedCommands.contains(command)) {
                Log.e(TAG, String.format("Cannot identify the command [%s]", command));
                setResultCode(Activity.RESULT_CANCELED);
                return;
            }
            boolean isSuccessful;
            if (command.equals(COMMAND_ENABLE)) {
                isSuccessful = getHandler(context).enable();
            } else {
                isSuccessful = getHandler(context).disable();
            }
            setResultCode(isSuccessful ? Activity.RESULT_OK : Activity.RESULT_CANCELED);
        } else if(intent.getExtras().containsKey(COMMAND_KEY_DATA)) {
            String data = intent.getStringExtra(COMMAND_KEY_DATA);
            if(TextUtils.isEmpty(data)) {
                setResultCode(Activity.RESULT_CANCELED);
                return;
            }
            boolean isSuccessful = getHandler(context).handleOtherData(context, intent, data);
            setResultCode(isSuccessful ? Activity.RESULT_OK : Activity.RESULT_CANCELED);
        }
    }

    protected abstract AbstractSettingHandler getHandler(Context context);

}

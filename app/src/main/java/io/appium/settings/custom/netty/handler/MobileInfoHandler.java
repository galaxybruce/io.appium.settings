package io.appium.settings.custom.netty.handler;

import android.content.Context;
import android.media.AudioManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import io.appium.settings.custom.utils.NetWorkUtils;
import io.appium.settings.custom.utils.Utils;
import io.appium.settings.custom.netty.DeviceInfo;
import io.appium.settings.custom.netty.protocol.TextProtocol;

/**
 * @author bruce.zhang
 * @date 2020-02-03 22:47
 * @description 获取手机基本信息，如ip地址，网络类型等
 * <p>
 * modification history:
 */
public class MobileInfoHandler implements IHandler {

    @Override
    public TextProtocol handle(Context context, String msg) {

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setSerialNo(Utils.getSerialNumber() );
        deviceInfo.setIp(NetWorkUtils.getIP(context));
        deviceInfo.setNetState(NetWorkUtils.isWifi(context) ? "WIFI" : "移动网络");

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        deviceInfo.setW(dm.widthPixels);
        deviceInfo.setH(dm.heightPixels);

        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        // 系统音量
        int systemVoiceMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
        int systemVoice = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
        deviceInfo.setVolume(systemVoice);

        return TextProtocol.newProtocol(TextProtocol.Header.MM_MOBILE_INFO, deviceInfo.toString());
    }
}

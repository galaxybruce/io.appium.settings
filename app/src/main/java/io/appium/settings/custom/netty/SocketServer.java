package io.appium.settings.custom.netty;

import android.content.Intent;
import android.text.TextUtils;

/**
 * @author bruce.zhang
 * @date 2020-02-13 09:38
 * @description (亲 ， 我是做什么的)
 * <p>
 * modification history:
 */
public class SocketServer {

    public static String SERVER_IP;
    public static final int PORT = 6656;

    public static void setServerIp(Intent intent) {
        String ip = intent.getStringExtra("serverIp");
        if(!TextUtils.isEmpty(ip)) {
            SERVER_IP = ip;
        }
    }

    public static String getServerIp() {
        if(!TextUtils.isEmpty(SERVER_IP)) {
            return SERVER_IP;
        } else {
            return "10.61.6.58";
        }
    }

}

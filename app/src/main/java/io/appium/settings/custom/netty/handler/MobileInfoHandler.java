package io.appium.settings.custom.netty.handler;

import android.content.Context;

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
        return TextProtocol.newProtocol(TextProtocol.Header.SMM_MOBILE_INFO, "MobileInfoHandler");
    }
}

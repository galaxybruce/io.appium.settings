package io.appium.settings.custom.netty.handler;

import android.content.Context;

import io.appium.settings.custom.netty.protocol.TextProtocol;

/**
 * @author bruce.zhang
 * @date 2020-02-03 22:47
 * @description (亲 ， 我是做什么的)
 * <p>
 * modification history:
 */
public interface IHandler {

    TextProtocol handle(Context context, String msg);
}

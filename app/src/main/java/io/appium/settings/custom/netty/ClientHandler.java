package io.appium.settings.custom.netty;


import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import io.appium.settings.custom.netty.handler.IHandler;
import io.appium.settings.custom.netty.handler.MobileInfoHandler;
import io.appium.settings.custom.netty.protocol.TextProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @author bruce.zhang
 * @date 2020-02-03 10:16
 * @description (亲 ， 我是做什么的)
 * <p>
 * modification history:
 */
class ClientHandler extends SimpleChannelInboundHandler<String> {

    private final Context context;
    private Map<String, IHandler> handlerMap = new HashMap<>();

    public ClientHandler(Context context) {
        this.context  = context;
        init();
    }

    private void init() {
        handlerMap.put(TextProtocol.Header.SMM_MOBILE_INFO, new MobileInfoHandler());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        TextProtocol protocol = TextProtocol.parseWithString(msg);
        IHandler handler = handlerMap.get(protocol.getProtocolHeader());
        if(handler != null) {
            TextProtocol result = handler.handle(context, protocol.getProtocolBody());
            ctx.channel().writeAndFlush(result.toString() + "\n");
        }
    }
}

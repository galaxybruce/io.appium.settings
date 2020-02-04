package io.appium.settings.custom.netty.protocol;

/**
 * @author bruce.zhang
 * @date 2020-02-03 18:10
 * @description (亲 ， 我是做什么的)
 * <p>
 * modification history:
 */

import androidx.annotation.NonNull;

import java.security.InvalidParameterException;

public class TextProtocol {
    public static class Header {
        // socket通信协议已 手机发送给pc已 MM_开头  pc发送给手机已 SMM_ 开头
        public final static String MM_HELLO = "MM_HELLO";
        public final static String MM_MOBILE_INFO = "MM_MOBILE_INFO";

        public final static String SMM_HELLO = "SMM_HELLO";
        public final static String SMM_MOBILE_INFO = "SMM_MOBILE_INFO";


    }

    private String protocolHeader = "";
    private String protocolBody = "";

    private TextProtocol() {

    }

    public static TextProtocol newProtocol(String protocolHeader, String protocolBody) {
        TextProtocol protocol = new TextProtocol();
        protocol.protocolHeader = protocolHeader;
        protocol.protocolBody = protocolBody;
        return protocol;
    }


    /**
     * 将字符串解析为协议
     * @return TextProtocol
     */
    public static TextProtocol parseWithString(String str) throws InvalidParameterException {

        int splitIndex = -1;
        if ((splitIndex = str.indexOf("://")) == -1) {
            throw new InvalidParameterException(str + " 不是一个合法的协议格式");
        }

        TextProtocol protocol = new TextProtocol();
        protocol.protocolHeader = str.substring(0, splitIndex);
        protocol.protocolBody = str.substring(splitIndex + 3);
        return protocol;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s://%s", protocolHeader, protocolBody);
    }

    public String getProtocolHeader() {
        return protocolHeader;
    }

    public String getProtocolBody() {
        return protocolBody;
    }
}

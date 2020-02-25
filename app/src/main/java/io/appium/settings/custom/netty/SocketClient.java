package io.appium.settings.custom.netty;

import android.content.Context;
import android.util.Log;

import io.appium.settings.custom.utils.Utils;
import io.appium.settings.custom.netty.protocol.TextProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class SocketClient {

    private Context context;
    private final String host;
    private final int port;
    private Thread clientThread;

    public SocketClient(Context context, String host, int port) {
        this.context = context;
        this.host = host;
        this.port = port;
    }

    public void start() {
        if (clientThread != null && clientThread.isAlive()) {
            return;
        }
        clientThread = new Thread() {
            @Override
            public void run() {

                EventLoopGroup worker = new NioEventLoopGroup();
                try {
                    Bootstrap bootstrap  = new Bootstrap()
                            .group(worker)
                            .channel(NioSocketChannel.class)
                            .handler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel ch) throws Exception {

                                    ChannelPipeline pipeline = ch.pipeline();
                                    pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                                    pipeline.addLast("decoder", new StringDecoder());
                                    pipeline.addLast("encoder", new StringEncoder());
                                    pipeline.addLast("io/appium/setting/handler", new ClientHandler(context));
                                }
                            });
                    Channel channel = bootstrap.connect(host, port).sync().channel();

                    // 发送设备号给服务端
                    channel.writeAndFlush(buildHelloMsg() + "\n");
                    Log.i("com.kidswant.ss-app", "SocketClient connect success !!!" );

                    channel.closeFuture().sync();
                } catch (Exception e) {
                    Log.i("com.kidswant.ss-app", "SocketClient connect fail !!! " );
                    Log.i("com.kidswant.ss-app", e.getLocalizedMessage());
                    this.interrupt();
                    clientThread = null;
                } finally {
                    worker.shutdownGracefully();
                }
            }
        };
        clientThread.start();
    }

    public void stop() {
        if (clientThread == null) {
            throw new IllegalStateException("Socket client is not running");
        }
        clientThread.interrupt();
    }

    private String buildHelloMsg() {
        TextProtocol protocol = TextProtocol.newProtocol(TextProtocol.Header.MM_HELLO, Utils.getSerialNumber());
        return protocol.toString();
    }

}

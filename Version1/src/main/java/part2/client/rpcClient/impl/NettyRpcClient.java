package part2.client.rpcClient.impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import part2.client.rpcClient.RpcClient;
import part2.common.message.RpcRequest;
import part2.common.message.RpcResponse;

public class NettyRpcClient implements RpcClient {
    private String host;
    private int port;
    private static final Bootstrap bootstrap;
    private static final EventLoopGroup eventLoopGroup;

    public NettyRpcClient(String host,int port) {
        this.host=host;
        this.port=port;
    }
    //netty客户端初始化
    static {
        eventLoopGroup=new NioEventLoopGroup();
        bootstrap=new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                //NettyClientInitializer里配置netty对消息的处理机制
                .handler(new NettyClientInitializer());
    }

    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        try {
            //创建一个channelFuture对象，代表这一个操作事件，sync方法表示阻塞直到connect完成
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            //channel表示一个连接的单位，类似socket
            Channel channel = channelFuture.channel();
            //发送数据
            channel.writeAndFlush(request);
            //sync()阻塞获取结果
            channel.closeFuture().sync();
            /*
            阻塞的获取结果，通过channel涉及别名，获取特定名字下的channel中的内容(这个在hanlder中设置)
            Attributekey是线程隔离的,不会有线程安全问题
            当前场景下选择堵塞获取结果
            其他场景也可以选择添加监听器的方式来异步获取结果 channelFuture.addListener .....
             */
            AttributeKey<Object> key = AttributeKey.valueOf("RPCResponse");
            RpcResponse response = (RpcResponse) channel.attr(key).get();

            System.out.println(response);
            return response;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

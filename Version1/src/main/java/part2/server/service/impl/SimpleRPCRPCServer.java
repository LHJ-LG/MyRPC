package part2.server.service.impl;

import lombok.AllArgsConstructor;
import part2.server.service.RpcServer;
import part2.server.provider.ServiceProvider;
import part2.server.service.work.WorkThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@AllArgsConstructor
public class SimpleRPCRPCServer implements RpcServer {
    private ServiceProvider serviceProvider;
    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务器启动了");
            while (true) {
                //如果没有连接，会阻塞在这里
                Socket socket = serverSocket.accept();
                //有连接，创建一个新的线程去处理
                new Thread(new WorkThread(socket,serviceProvider)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}

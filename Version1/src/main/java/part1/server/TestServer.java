package part1.server;

import part1.common.service.impl.UserServiceImpl;
import part1.server.provider.ServiceProvider;
import part1.server.service.impl.SimpleRPCRPCServer;

public class TestServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.providerServiceInterface(userService);

        SimpleRPCRPCServer rpcServer = new SimpleRPCRPCServer(serviceProvider);

        rpcServer.start(9999);
    }
}

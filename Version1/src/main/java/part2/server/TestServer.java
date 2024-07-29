package part2.server;

import part1.common.service.impl.UserServiceImpl;
import part2.server.provider.ServiceProvider;
import part2.server.service.impl.SimpleRPCRPCServer;

public class TestServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.providerServiceInterface(userService);

        SimpleRPCRPCServer rpcServer = new SimpleRPCRPCServer(serviceProvider);

        rpcServer.start(9999);
    }
}

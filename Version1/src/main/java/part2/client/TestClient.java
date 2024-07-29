package part2.client;

import part1.common.pojo.User;
import part1.common.service.UserService;
import part2.client.proxy.ClientProxy;

public class TestClient {
    public static void main(String[] args) {
        //创建ClientProxy对象
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 9999);
        //通过ClientProxy对象获取代理对象
        UserService proxy = clientProxy.getProxy(UserService.class);
        //调用代理对象的方法
        User user = proxy.getUserByUserId(1);
        System.out.println("从服务端获得的user="+user.toString());
    }
}

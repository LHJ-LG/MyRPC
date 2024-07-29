package part2.client.rpcClient;

import part2.common.message.RpcRequest;
import part2.common.message.RpcResponse;

public interface RpcClient {
    //底层通信方法
    RpcResponse sendRequest(RpcRequest request);
}

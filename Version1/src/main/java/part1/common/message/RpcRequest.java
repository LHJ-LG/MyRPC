package part1.common.message;

import lombok.Builder;
import lombok.Data;

/**
 * 定义请求信息的格式RpcRequest
 */
@Data
@Builder
public class RpcRequest {
    //服务类名，客户端只知道接口
    private String interfaceName;
    //调用的方法名
    private String methodName;
    //参数列表
    private Object[] params;
    //参数列表
    private Class<?>[] paramsType;
}

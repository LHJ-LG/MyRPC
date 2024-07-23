package part1.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // 客户端和服务端共有的
    private Integer id;
    private String userName;
    private Boolean sex;
}

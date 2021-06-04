package com.hbnu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SysResult {
    private Integer status;    //状态码：200正常，201非正常
    private String meg;     //服务器端返回给客户端的消息
    private Object data;    //服务器端返回的数据

    public static SysResult success() {
        return new SysResult(200, null, null);
    }

    public static SysResult success(String meg) {
        return new SysResult(200, meg, null);
    }

    public static SysResult success(Object data) {
        return new SysResult(200, null, data);
    }

    public static SysResult fail() {
        return new SysResult(201, null, null);
    }

    public static SysResult fail(String meg) {
        return new SysResult(201, meg, null);
    }
}


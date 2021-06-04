package com.hbnu.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class BasePojo implements Serializable {
    private static final long serialVersionUID = 1536592393107777968L;
    
    private Date created; //创建时间
    private Date updated; //更新时间
}

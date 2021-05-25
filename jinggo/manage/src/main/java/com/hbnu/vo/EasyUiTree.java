package com.hbnu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//get set方法
@Data
@Accessors(chain = true)
//有参无参构造
@NoArgsConstructor
@AllArgsConstructor
public class EasyUiTree {
    private Long id;
    private String text;
    private String state;
}

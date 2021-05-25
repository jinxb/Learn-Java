package com.hbnu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ImageVo {
    private Integer error = 0; //0代表上传成功，1代表失败
    private String url; //虚拟路径
    private Integer width;  //宽
    private Integer height; //高

    public static ImageVo fail(){
        return new ImageVo(1,null,null,null);
    }
}

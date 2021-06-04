package com.hbnu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ImageVO {
    private Integer error = 0;  //0:代表图片，1：代表图片上传失败
    private String url; //图片的虚拟路径
    private Integer width;
    private Integer height;

    public static ImageVO fail() {
        return new ImageVO(1, null, null, null);
    }

}

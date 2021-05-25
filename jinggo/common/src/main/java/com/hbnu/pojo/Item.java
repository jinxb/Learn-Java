package com.hbnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("tb_item")
@JsonIgnoreProperties(ignoreUnknown = true) //转json时 忽略未知属性
public class Item extends BasePojo{

    @TableId(type = IdType.AUTO)
    private Long id; //商品id
    private String title; //标题
    private String sellPoint; //卖点
    private Long price; //
    private Integer num;
    private String barcode;
    private String image;
    private Long cid; //分类信息
    private Integer status; //1上架 2下架

    //多张图片url字符串分割
    public String[] getImages(){
        return image.split(",");
    }
}

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item extends BasePojo {

    @TableId(type = IdType.AUTO)
    private Long id; //商品id
    private String title; //商品标题
    private String sellPoint; //商品卖点
    private Long price; //商品价格
    private Integer num; //商品库存
    private String barcode; //商品二维码
    private String image; //商品图片
    private Long cid; //商品分类id
    private Integer status; //商品状态 0：上架 1：下架

    public String[] getImages() {
        return image.split(",");
    }
}

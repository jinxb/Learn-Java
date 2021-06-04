package com.hbnu.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_item_desc")
public class ItemDesc extends BasePojo {

    @TableId
    private Long itemId;    //商品描述id
    private String itemDesc;    //商品描述信息
}

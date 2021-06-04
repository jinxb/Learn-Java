package com.hbnu.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("tb_item_cat")
public class ItemCat extends BasePojo {

    @TableId(type = IdType.AUTO)
    private Long id;    //类目id
    private Long parentId;  //父类目id
    private String name;    //类目名称
    private Integer status; //状态
    private Integer sortOrder;  //排列序号
    private Boolean isParent;   //该类目是否为父类目，1为true，0为false

}

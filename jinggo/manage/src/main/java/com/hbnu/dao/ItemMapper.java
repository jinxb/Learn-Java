package com.hbnu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbnu.pojo.Item;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item> {

    @Select("SELECT * FROM tb_item ORDER BY updated DESC LIMIT #{page},#{rows};")
    List<Item> findTableByPage(@Param("page") int start, @Param("rows") Integer rows);

    @Update("UPDATE tb_item SET status=#{status} WHERE id in #{ids};")
    void updateStatusById(@Param("status") Integer status,@Param("ids") List<Long> ids);
}

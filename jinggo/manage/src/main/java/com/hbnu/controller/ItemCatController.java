package com.hbnu.controller;

import com.hbnu.service.ItemCatService;
import com.hbnu.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    //url地址：/item/cat/queryItemName
    @RequestMapping("/queryItemName")
    public String findItemCatName(Long itemCatId) {
        String itemCat = itemCatService.findItemCatById(itemCatId).getName();
        return itemCat;
    }

    /**
     * url地址：/item/cat/list
     * 返回值: List<EasyUITree>
     * 需求:查询parentId=0
     * <p>
     * springmvc动态参数接收
     * 传过来的参数名称id
     * 目的：将id当作parentId
     * 要求：parentId默认值为0
     *
     * @return
     */
    @RequestMapping("/list")
    public List<EasyUITree> findItemCatByParentId(@RequestParam(value = "id", defaultValue = "0") Long parentId) {

        return itemCatService.findEasyUITree(parentId);
    }
}

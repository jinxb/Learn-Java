package com.hbnu.controller;

import com.hbnu.pojo.ItemCat;
import com.hbnu.service.ItemCatService;
import com.hbnu.vo.EasyUiTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    //URL地址：/item/cat/queryItemName
    @RequestMapping("/queryItemName")
    public String findItemCatName(Long itemCatId) {
        ItemCat itemCat = itemCatService.findItemCatById(itemCatId);
        return itemCat.getName();
    }

    /**
     * URL地址：/item/cat/list
     * 要求：
     * 返回值：List<EasyUiTree>
     * 需求：查询parentId = 0
     *
     * springmvc 动态参数接收    @RequestParam用于动态参数接收
     * 传过来的参数名：id
     * 目的：将id当作parentId
     * 要求：parentId默认值为0
     *
     * @return
     */
    @RequestMapping("/list")
    public List<EasyUiTree> findItemCatByParentId(@RequestParam(value="id",defaultValue = "0") Long parentId) {

//        List<EasyUiTree> easyUiTreeList = itemCatService.findEasyTree(parentId);
        return itemCatService.findEasyTree(parentId);
    }
}

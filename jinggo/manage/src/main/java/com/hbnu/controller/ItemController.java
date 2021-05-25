package com.hbnu.controller;

import com.hbnu.pojo.Item;
import com.hbnu.pojo.ItemDesc;
import com.hbnu.service.ItemService;
import com.hbnu.vo.EasyUiTable;
import com.hbnu.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/query")
    public EasyUiTable findTableByPage(Integer page, Integer rows) {
        return itemService.findTableByPage(page, rows);
    }


    /**
     * URL地址：/item/save
     * 请求参数：cid=3&name=jacak
     *
     * @return
     */

    @RequestMapping("/save")
    public SysResult saveItem(Item item, ItemDesc itemDesc) {
        itemService.saveItem(item,itemDesc);
        return SysResult.success();
    }

    @RequestMapping("/update")
    public SysResult updateItem(Item item,ItemDesc itemDesc){
        itemService.updateItem(item,itemDesc);
        return SysResult.success();
    }

    @RequestMapping("/delete")
    public SysResult deleteItem(Long[] ids) {
        itemService.deleteItem(ids);
        return SysResult.success();
    }


    @RequestMapping("/instock")
    public SysResult instockItem(Long[] ids) {
        itemService.instockItem(ids);
        return SysResult.success();
    }

    @RequestMapping("/reshelf")
    public SysResult reshelfItem(Long[] ids) {
        itemService.reshelfItem(ids);
        return SysResult.success();
    }

    // URL /item/query/item/desc/+data.id
    @RequestMapping("/query/item/desc/{itemId}")
    public SysResult findItemDescByItemId(@PathVariable Long itemId){
        ItemDesc itemDesc = itemService.findItemDescByItemId(itemId);
        // 将商品详情数据回显给前端
        return SysResult.success(itemDesc);
    }
}

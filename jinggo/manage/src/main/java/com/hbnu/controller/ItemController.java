package com.hbnu.controller;

import com.hbnu.pojo.Item;
import com.hbnu.pojo.ItemDesc;
import com.hbnu.service.ItemService;
import com.hbnu.vo.EasyTable;
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
    public EasyTable findItemByPage(Integer page, Integer rows) {
        return itemService.findItemByPage(page, rows);
    }

    /**
     * url:/item/save
     * 请求参数：cid=3&name=jack
     *
     * @return
     */
    @RequestMapping("/save")
    public SysResult saveItem(Item item, ItemDesc itemDesc) {
        itemService.saveItem(item, itemDesc);
        return SysResult.success();
    }

    @RequestMapping("/update")
    public SysResult updateItem(Item item, ItemDesc itemDesc) {
        itemService.updateItem(item, itemDesc);
        return SysResult.success();
    }

    @RequestMapping("/delete")
    public SysResult deleteItem(Long[] ids) {
        itemService.deleteItem(ids);
        return SysResult.success();
    }

    @RequestMapping("/instock")
    public SysResult instockItem(Long[] ids) {
        Integer status = 2;
        itemService.updateItemStatus(status, ids);
        return SysResult.success();
    }

    @RequestMapping("/reshelf")
    public SysResult reshelfItem(Long[] ids) {
        Integer status = 1;
        itemService.updateItemStatus(status, ids);
        return SysResult.success();
    }

    @RequestMapping("/query/item/desc/{itemId}")
    public SysResult findItemDescByItemId(@PathVariable Long itemId) {
        ItemDesc itemDesc = itemService.findItemDescByItemId(itemId);
        //将商品信息数据返回给前端页面进行回显
        return SysResult.success(itemDesc);
    }
}

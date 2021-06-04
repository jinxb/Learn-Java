package com.hbnu.service;

import com.hbnu.pojo.Item;
import com.hbnu.pojo.ItemDesc;
import com.hbnu.vo.EasyTable;

public interface ItemService {
    EasyTable findItemByPage(Integer page, Integer rows);

    void saveItem(Item item, ItemDesc itemDesc);

    void updateItem(Item item, ItemDesc itemDesc);

    void deleteItem(Long[] ids);

    void updateItemStatus(Integer status, Long[] ids);

    ItemDesc findItemDescByItemId(Long itemId);
}

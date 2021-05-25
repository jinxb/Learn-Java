package com.hbnu.service;

import com.hbnu.pojo.Item;
import com.hbnu.pojo.ItemDesc;
import com.hbnu.vo.EasyUiTable;


public interface ItemService {

    EasyUiTable findTableByPage(Integer page, Integer rows);

    void saveItem(Item item, ItemDesc itemDesc);

    void updateItem(Item item,ItemDesc itemDesc);

    void deleteItem(Long[] ids);

    void instockItem(Long[] ids);

    void reshelfItem(Long[] ids);

    ItemDesc findItemDescByItemId(Long itemId);
}

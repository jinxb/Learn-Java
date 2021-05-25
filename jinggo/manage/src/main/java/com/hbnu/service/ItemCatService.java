package com.hbnu.service;

import com.hbnu.pojo.ItemCat;
import com.hbnu.vo.EasyUiTree;

import java.util.List;

public interface ItemCatService {
    ItemCat findItemCatById(Long itemCatId);

    List<EasyUiTree> findEasyTree(Long parentId);
}

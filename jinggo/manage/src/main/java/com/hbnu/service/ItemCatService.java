package com.hbnu.service;

import com.hbnu.pojo.ItemCat;
import com.hbnu.vo.EasyUITree;

import java.util.List;

public interface ItemCatService {
    ItemCat findItemCatById(Long itemCatId);

    List<EasyUITree> findEasyUITree(Long parentId);
}

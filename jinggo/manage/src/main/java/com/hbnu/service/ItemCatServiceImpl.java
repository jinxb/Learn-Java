package com.hbnu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbnu.dao.ItemCatMapper;
import com.hbnu.pojo.ItemCat;
import com.hbnu.vo.EasyUiTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public ItemCat findItemCatById(Long itemCatId) {
        ItemCat itemCat = itemCatMapper.selectById(itemCatId);
        return itemCat;
    }

    /**
     * pojo -> vo
     * 1.通过parentId查询数据库，List<ItemCat>
     * 2.遍历List<ItemCat>集合，将itemCat中需要的属性封装到EasyUiTree对象中
     * 3.将EasyUiTree对象添加到集合中
     *
     * @param parentId 父类Id
     * @return 商品分类值对象数据
     */
    @Override
    public List<EasyUiTree> findEasyTree(Long parentId) {

        List<EasyUiTree> treeList = new ArrayList<>();

        // 1.根据ParentId获取ItemCat数据
        List<ItemCat> itemCatList = findItemCatByParentId(parentId);

        // 2.遍历itemCatList
        for (ItemCat itemCat : itemCatList) {
            Long id = itemCat.getId();
            String text = itemCat.getName();
            // 如果是父级closed 否则open
            String state = itemCat.getIsParent() ? "closed" : "open";

            EasyUiTree easyUiTree = new EasyUiTree(id, text, state);

            // 3.将easyUiTree对象添加到集合
            treeList.add(easyUiTree);
        }
        return treeList;
    }


    private List<ItemCat> findItemCatByParentId(Long parentId) {
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);
        return itemCatList;
    }
}

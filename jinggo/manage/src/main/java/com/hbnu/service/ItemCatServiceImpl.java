package com.hbnu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbnu.dao.ItemCatMapper;
import com.hbnu.pojo.ItemCat;
import com.hbnu.vo.EasyUITree;
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
     * pojo -----转换----- vo
     * 思路:
     * 1、通过parentId查询数据库，List<ItemCat>
     * 2、遍历List<ItemCat>， 将itemCat中 需要的属性封装到EasyUITree对象中
     * 3、将第二步中的EasyUITree对象添加到集合中
     *
     * @param parentId 父类id
     * @return
     */
    @Override
    public List<EasyUITree> findEasyUITree(Long parentId) {
        List<EasyUITree> treeList = new ArrayList<>();

        //1、根据parentId获取itemCat数据
        List<ItemCat> itemCatList = findItemCatByParentId(parentId);

        //2、遍历
        for (ItemCat itemCat : itemCatList) {
            Long id = itemCat.getId();
            String text = itemCat.getName();
            //如果是父级closed，否则open
            String state = itemCat.getIsParent() ? "closed" : "open";
            
            EasyUITree easyUITree = new EasyUITree(id, text, state);

            //3、将easyUITree添加到集合
            treeList.add(easyUITree);
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

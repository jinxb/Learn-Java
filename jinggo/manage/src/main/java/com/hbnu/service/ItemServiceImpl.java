package com.hbnu.service;

import com.hbnu.dao.ItemDescMapper;
import com.hbnu.dao.ItemMapper;
import com.hbnu.pojo.Item;
import com.hbnu.pojo.ItemDesc;
import com.hbnu.vo.EasyTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public EasyTable findItemByPage(Integer page, Integer rows) {
        //1、查询所有商品的数量
        Integer total = itemMapper.selectCount(null);

        //2、查询商品信息
        int start = (page - 1) * rows;
        List<Item> itemList = itemMapper.findItemByPage(start, rows);

        return new EasyTable(total, itemList);
    }

    /**
     * 新增商品信息
     *
     * @param item 商品信息
     */
    @Override
    @Transactional
    public void saveItem(Item item, ItemDesc itemDesc) {
        //保存商品信息
        item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
        itemMapper.insert(item);

        //主键自动增长，商品入库后才会有id
        //mybatis特性：商品入库之后主键会自动回填

        //保存商品描述信息
        itemDesc.setItemId(item.getId()).setCreated(item.getCreated()).setUpdated(item.getUpdated());
        itemDescMapper.insert(itemDesc);

    }

    /**
     * 更新商品信息
     *
     * @param item 商品信息
     */
    @Override
    @Transactional
    public void updateItem(Item item, ItemDesc itemDesc) {
        item.setUpdated(new Date());
        itemMapper.updateById(item);

        itemDesc.setItemId(item.getId()).setUpdated(new Date());
        itemDescMapper.updateById(itemDesc);
    }

    /**
     * 删除商品
     *
     * @param ids 商品id
     */
    @Override
    @Transactional
    public void deleteItem(Long[] ids) {
        //将数组转为集合
        List<Long> idList = Arrays.asList(ids);
        itemMapper.deleteBatchIds(idList);
        itemDescMapper.deleteBatchIds(idList);
    }

    @Override
    public void updateItemStatus(Integer status, Long[] ids) {
        for (Long id : ids) {
            Item item = new Item();
            item.setId(id).setStatus(status).setUpdated(new Date());
            itemMapper.updateById(item);
        }
    }

    @Override
    public ItemDesc findItemDescByItemId(Long itemId) {
        return itemDescMapper.selectById(itemId);
    }
}

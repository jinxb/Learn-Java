package com.hbnu.service;

import com.hbnu.dao.ItemDescMapper;
import com.hbnu.pojo.Item;
import com.hbnu.dao.ItemMapper;
import com.hbnu.pojo.ItemDesc;
import com.hbnu.vo.EasyUiTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public EasyUiTable findTableByPage(Integer page, Integer rows) {
        //查询所有商品数量
        Integer total =  itemMapper.selectCount(null);

        //查询商品信息
        int start = (page - 1) * rows;
        List<Item> itemList = itemMapper.findTableByPage(start,rows);
        return new EasyUiTable(total,itemList);
    }

    @Override
    @Transactional
    public void saveItem(Item item, ItemDesc itemDesc) {

        //  保存商品信息
        item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
        itemMapper.insert(item);
        // 主键设置自动增长，商品入库之后才会有Id
        // mybatis(特性)：商品入库之后主键会自动回填

        //  保存商品详细描述信息
        itemDesc.setItemId(item.getId()).setCreated(item.getCreated()).setUpdated(item.getCreated());
        itemDescMapper.insert(itemDesc);

    }

    @Override
    @Transactional //事务
    public void updateItem(Item item,ItemDesc itemDesc) {
        item.setUpdated(new Date());
        itemMapper.updateById(item);

        itemDesc.setItemId(item.getId()).setUpdated(new Date());
        itemDescMapper.updateById(itemDesc);
    }

    @Override
    public void deleteItem(Long[] ids) {
        // 将数组转为集合
        List<Long> idList = Arrays.asList(ids);
        // 删除多条数据
        itemMapper.deleteBatchIds(idList);

        //  删除详情表数据
        itemDescMapper.deleteBatchIds(idList);
    }

    @Override
    public void instockItem(Long[] ids) {
        Integer status = 2;
//        itemMapper.updateStatusById(status,ids);
        updateStatus(ids,status);

    }


    @Override
    public void reshelfItem(Long[] ids) {
        Integer status = 1;
        updateStatus(ids,status);
    }

    @Override
    public ItemDesc findItemDescByItemId(Long itemId) {
        return itemDescMapper.selectById(itemId);
    }


    private void updateStatus(Long[] ids,int status) {
        for(Long id : ids){
            Item item = new Item();
            item.setId(id).setStatus(status);
            itemMapper.updateById(item);
        }
    }
}

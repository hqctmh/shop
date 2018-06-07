package cn.mh.persist.dao;


import cn.mh.entity.Item;
import cn.mh.persist.example.ItemExample;
import cn.mh.persist.example.ItemExample.Criteria;
import cn.mh.persist.mapper.ItemMapper;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDao {
    @Autowired
    private ItemMapper itemMapper;

    public boolean addItem(Item item){
        return itemMapper.insert(item)>0;
    }

    public Item findItemById(String id){
        return itemMapper.selectByPrimaryKey(id);
    }
    public List<Item> findByIds(List<String> ids){
        ItemExample itemExample=new ItemExample();
        itemExample.createCriteria().andIdIn(ids);
        List<Item> list=itemMapper.selectByExample(itemExample);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
    public List<Item> findItemByLimit(String type,String iid){
        ItemExample itemExample=new ItemExample();
        Criteria criteria=itemExample.createCriteria();
        if(StringUtil.isNotBlank(type)){
            criteria.andTypeEqualTo(type);
        }
        if(StringUtil.isNotBlank(iid)){
            criteria.andIidEqualTo(iid);
        }
        List<Item> list=itemMapper.selectByExample(itemExample);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
}

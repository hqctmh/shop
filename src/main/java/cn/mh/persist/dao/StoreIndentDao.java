package cn.mh.persist.dao;


import cn.mh.entity.StoreIndent;
import cn.mh.persist.example.StoreIndentExample;
import cn.mh.persist.mapper.StoreIndentMapper;
import cn.mh.util.DateUtil;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public class StoreIndentDao {
    @Autowired
    private StoreIndentMapper storeIndentMapper;

    public boolean addStoreIndent(StoreIndent storeIndent){
        return storeIndentMapper.insert(storeIndent)>0;
    }

    public boolean addStoreIndentBatch(List<StoreIndent> storeIndentList){
        return storeIndentMapper.insertBatch(storeIndentList)>0;
    }
    public boolean updateStoreIndent(StoreIndent storeIndent){
        return storeIndentMapper.updateByPrimaryKeySelective(storeIndent)>0;
    }
    public StoreIndent findStoreIndentById(String id){
        return storeIndentMapper.selectByPrimaryKey(id);
    }
    public boolean delStoreIndent(String id){
        return storeIndentMapper.deleteByPrimaryKey(id)>0;
    }

    public List<StoreIndent> findStoreIndentbyIds(List<String> idList){
        StoreIndentExample example=new StoreIndentExample();
        StoreIndentExample.Criteria criteria=example.createCriteria();
        criteria.andIdIn(idList);
        List<StoreIndent> list=storeIndentMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
    public List<StoreIndent> findStoreIndentbyIndentId(String indentId){
        StoreIndentExample example=new StoreIndentExample();
        StoreIndentExample.Criteria criteria=example.createCriteria();
        criteria.andIndentIdEqualTo(indentId);
        List<StoreIndent> list=storeIndentMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public boolean updateBantch(List<StoreIndent> list){
        return storeIndentMapper.updateBatch(list)>0;
    }

    public BigDecimal sumPrice(Date time,String storeId){
        Date start=DateUtil.getDayBeginTime(time);
        Date end= DateUtil.getDayEndTime(time);
        StoreIndentExample example=new StoreIndentExample();
        StoreIndentExample.Criteria criteria=example.createCriteria();
        criteria.andCreateTimeBetween(start,end);
        if(StringUtil.isNotBlank(storeId)){
            criteria.andStoreIdEqualTo(storeId);
        }
        BigDecimal price=storeIndentMapper.sumPrice(example);
        return price;
    }
    
}

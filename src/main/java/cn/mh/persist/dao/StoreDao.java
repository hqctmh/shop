package cn.mh.persist.dao;


import cn.mh.entity.Store;
import cn.mh.persist.example.StoreExample;
import cn.mh.persist.example.StoreExample.Criteria;
import cn.mh.persist.mapper.StoreMapper;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoreDao {
    @Autowired
    private StoreMapper storeMapper;

    public boolean addStore(Store store){
        return storeMapper.insert(store)>0;
    }
    public boolean updateStore(Store store){
        return storeMapper.updateByPrimaryKeySelective(store)>0;
    }
    public Store findStoreById(String id){
        return storeMapper.selectByPrimaryKey(id);
    }
    public boolean delStore(String id){
        return storeMapper.deleteByPrimaryKey(id)>0;
    }

    public List<Store> findStoreByLimit(String userId,String storeName,String status){
        StoreExample example=new StoreExample();
        Criteria criteria=example.createCriteria();
        staticSet(criteria,userId, storeName, status);
        List<Store> list=storeMapper.selectByExample(example);
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public Long getStoreCount(String userId,String storeName,String status){
        StoreExample example=new StoreExample();
        Criteria criteria=example.createCriteria();
        staticSet(criteria,userId, storeName, status);
        return storeMapper.countByExample(example);
    }

    public List<Store> findStoreForPage(int pageNum,int pageSize,String userId,String storeName,String status){
        StoreExample example=new StoreExample();
        Criteria criteria=example.createCriteria();
        staticSet(criteria,userId, storeName, status);
        example.setPaging(true);
        example.setRowIndex(pageNum);
        example.setPageSize(pageSize);
        example.setOrderByClause("create_time desc");
        List<Store> list=storeMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public List<Store> findStoreByIds(List<String> ids){
        StoreExample example=new StoreExample();
        Criteria criteria=example.createCriteria();
        criteria.andIdIn(ids);
        List<Store> list=storeMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }



    private static void  staticSet(Criteria criteria,String userId,String storeName,String status){
        if(StringUtil.isNotBlank(userId)){
            criteria.andUserIdEqualTo(userId);
        }
        if(StringUtil.isNotBlank(storeName)){
            criteria.andStoreNameLike("%"+storeName+"%");
        }
        if(StringUtil.isNotBlank(status)){
            criteria.andStatusEqualTo(status);
        }
    }


}

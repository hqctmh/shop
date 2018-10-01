package cn.mh.persist.dao;


import cn.mh.entity.StoreApply;
import cn.mh.persist.example.StoreApplyExample;
import cn.mh.persist.mapper.StoreApplyMapper;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoreApplyDao {
    @Autowired
    private StoreApplyMapper storeApplyMapper;

    public boolean addStoreApply(StoreApply storeApply){
        return storeApplyMapper.insert(storeApply)>0;
    }
    public boolean updateStoreApply(StoreApply storeApply){
        return storeApplyMapper.updateByPrimaryKeySelective(storeApply)>0;
    }
    public StoreApply findStoreApplyById(String id){
        return storeApplyMapper.selectByPrimaryKey(id);
    }
    public boolean delStoreApply(String id){
        return storeApplyMapper.deleteByPrimaryKey(id)>0;
    }
    public List<StoreApply> findStoreApplyByLimit(String userId,String status){
        StoreApplyExample example=new StoreApplyExample();
        StoreApplyExample.Criteria criteria=example.createCriteria();
        if(StringUtil.isNotBlank(userId)){
            criteria.andUserIdEqualTo(userId);
        }
        if(StringUtil.isNotBlank(status)){
            criteria.andStatusEqualTo(status);
        }
        List<StoreApply> list=storeApplyMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }


}

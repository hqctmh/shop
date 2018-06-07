package cn.mh.persist.dao;


import cn.mh.entity.IndentDetail;
import cn.mh.persist.example.IndentDetailExample;
import cn.mh.persist.mapper.IndentDetailMapper;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IndentDetailDao {
    @Autowired
    private IndentDetailMapper indentDetailMapper;

    public boolean addIndentDetail(IndentDetail indentDetail){
        return indentDetailMapper.insert(indentDetail)>0;
    }

    public boolean addIndentDetailBatch(List<IndentDetail> indentDetailList){
        return indentDetailMapper.insertBatch(indentDetailList)>0;
    }
    public boolean updateIndentDetail(IndentDetail indentDetail){
        return indentDetailMapper.updateByPrimaryKeySelective(indentDetail)>0;
    }
    public IndentDetail findIndentDetailById(String id){
        return indentDetailMapper.selectByPrimaryKey(id);
    }
    public boolean delIndentDetail(String id){
        return indentDetailMapper.deleteByPrimaryKey(id)>0;
    }

    public List<IndentDetail> findIndentDetailByLimit(String indentId,String storeIndentId){
        IndentDetailExample example=new IndentDetailExample();
        IndentDetailExample.Criteria criteria=example.createCriteria();
        if(StringUtil.isNotBlank(indentId)){
            criteria.andIndentIdEqualTo(indentId);
        }
        if(StringUtil.isNotBlank(storeIndentId)){
            criteria.andStoreIndentIdEqualTo(storeIndentId);
        }
        List<IndentDetail> list=indentDetailMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
    public List<IndentDetail> findIndentDetailByStoreIndentIds(List<String> storeIndentIds){
        IndentDetailExample example=new IndentDetailExample();
        IndentDetailExample.Criteria criteria=example.createCriteria();
        criteria.andStoreIndentIdIn(storeIndentIds);
        List<IndentDetail> list=indentDetailMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
    
}

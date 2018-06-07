package cn.mh.persist.dao;

import cn.mh.entity.ReturnView;
import cn.mh.persist.example.ReturnViewExample;
import cn.mh.persist.mapper.ReturnViewMapper;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReturnViewDao {
    @Autowired
    private ReturnViewMapper returnViewMapper;

    public Long countByLimit(String storeIndentId,String storeId,String userId,String status){
        ReturnViewExample example=new ReturnViewExample();
        ReturnViewExample.Criteria criteria=example.createCriteria();
        if(StringUtil.isNotBlank(storeIndentId)){
            criteria.andStoreIndentIdEqualTo(storeIndentId);
        }
        if(StringUtil.isNotBlank(storeId)){
            criteria.andStoreIdEqualTo(storeId);
        }
        if(StringUtil.isNotBlank(userId)){
            criteria.andUserIdEqualTo(userId);
        }
        if(StringUtil.isNotBlank(status)){
            criteria.andStatusEqualTo(status);
        }
        return returnViewMapper.countByExample(example);
    }

    public List<ReturnView> findReturnViewForPage(int pageNum,int pageSize,String storeIndentId,String storeId,String userId,String status){
        ReturnViewExample example=new ReturnViewExample();
        ReturnViewExample.Criteria criteria=example.createCriteria();
        if(StringUtil.isNotBlank(storeIndentId)){
            criteria.andStoreIndentIdEqualTo(storeIndentId);
        }
        if(StringUtil.isNotBlank(storeId)){
            criteria.andStoreIdEqualTo(storeId);
        }
        if(StringUtil.isNotBlank(userId)){
            criteria.andUserIdEqualTo(userId);
        }
        if(StringUtil.isNotBlank(status)){
            criteria.andStatusEqualTo(status);
        }
        example.setPaging(true);
        example.setRowIndex(pageNum);
        example.setPageSize(pageSize);
        example.setOrderByClause("create_time desc");
        List<ReturnView> list=returnViewMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public List<ReturnView> findReturnViewByMainId(String mainId){
        ReturnViewExample example=new ReturnViewExample();
        ReturnViewExample.Criteria criteria=example.createCriteria();
        criteria.andMainIdEqualTo(mainId);
        List<ReturnView> list=returnViewMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }


}

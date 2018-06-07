package cn.mh.persist.dao;

import cn.mh.entity.IndentView;
import cn.mh.persist.example.IndentViewExample;
import cn.mh.persist.mapper.IndentViewMapper;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IndentViewDao {
    @Autowired
    private IndentViewMapper indentViewMapper;
    
    public List<IndentView> findIndentViewForPage(int pageNum,int pageSize,String userId,String storeId,String indentId,List<String> statusList){
        IndentViewExample example=new IndentViewExample();
        IndentViewExample.Criteria criteria=example.createCriteria();
        if(StringUtil.isNotBlank(userId)){
            criteria.andUserIdEqualTo(userId);
        }
        if(StringUtil.isNotBlank(storeId)){
            criteria.andStoreIdEqualTo(storeId);
        }
        if(StringUtil.isNotBlank(indentId)){
            criteria.andIndentIdEqualTo(indentId);
        }
        if(statusList!=null&&statusList.size()>0){
            criteria.andStatusIn(statusList);
        }
        example.setPaging(true);
        example.setRowIndex(pageNum);
        example.setPageSize(pageSize);
        example.setOrderByClause("order_num desc");
        List<IndentView> list=indentViewMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public Long getCount(String userId,String storeId,String indentId,List<String> statusList){
        IndentViewExample example=new IndentViewExample();
        IndentViewExample.Criteria criteria=example.createCriteria();
        if(StringUtil.isNotBlank(userId)){
            criteria.andUserIdEqualTo(userId);
        }
        if(StringUtil.isNotBlank(storeId)){
            criteria.andStoreIdEqualTo(storeId);
        }
        if(StringUtil.isNotBlank(indentId)){
            criteria.andIndentIdEqualTo(indentId);
        }
        if(statusList!=null&&statusList.size()>0){
            criteria.andStatusIn(statusList);
        }
        return indentViewMapper.countByExample(example);
    }
}

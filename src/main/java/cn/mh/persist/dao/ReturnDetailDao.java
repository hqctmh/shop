package cn.mh.persist.dao;


import cn.mh.entity.ReturnDetail;
import cn.mh.persist.mapper.ReturnDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReturnDetailDao {
    @Autowired
    private ReturnDetailMapper returnDetailMapper;

    public boolean addReturnDetail(ReturnDetail returnDetail){
        return returnDetailMapper.insert(returnDetail)>0;
    }
    public boolean addBatch(List<ReturnDetail>list){
        return returnDetailMapper.insertBatch(list)>0;
    }
    public boolean updateReturnDetail(ReturnDetail returnDetail){
        return returnDetailMapper.updateByPrimaryKeySelective(returnDetail)>0;
    }
    public ReturnDetail findReturnDetailById(String id){
        return returnDetailMapper.selectByPrimaryKey(id);
    }
    public boolean delReturnDetail(String id){
        return returnDetailMapper.deleteByPrimaryKey(id)>0;
    }
    
}

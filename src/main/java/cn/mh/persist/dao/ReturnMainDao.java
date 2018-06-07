package cn.mh.persist.dao;


import cn.mh.entity.ReturnMain;
import cn.mh.persist.mapper.ReturnMainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReturnMainDao {
    @Autowired
    private ReturnMainMapper returnMainMapper;

    public boolean addReturnMain(ReturnMain returnMain){
        return returnMainMapper.insert(returnMain)>0;
    }
    public boolean updateReturnMain(ReturnMain returnMain){
        return returnMainMapper.updateByPrimaryKeySelective(returnMain)>0;
    }
    public ReturnMain findReturnMainById(String id){
        return returnMainMapper.selectByPrimaryKey(id);
    }
    public boolean delReturnMain(String id){
        return returnMainMapper.deleteByPrimaryKey(id)>0;
    }

}

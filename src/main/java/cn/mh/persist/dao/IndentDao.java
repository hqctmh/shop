package cn.mh.persist.dao;


import cn.mh.entity.Indent;
import cn.mh.persist.example.IndentDetailExample;
import cn.mh.persist.example.IndentExample;
import cn.mh.persist.mapper.IndentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IndentDao {
    @Autowired
    private IndentMapper indentMapper;

    public boolean addIndent(Indent indent){
        return indentMapper.insert(indent)>0;
    }
    public boolean updateIndent(Indent indent){
        return indentMapper.updateByPrimaryKeySelective(indent)>0;
    }
    public Indent findIndentById(String id){
        return indentMapper.selectByPrimaryKey(id);
    }
    public boolean delIndent(String id){
        return indentMapper.deleteByPrimaryKey(id)>0;
    }

    public Indent findLastIndent(String userId){
        IndentExample indentExample=new IndentExample();
        IndentExample.Criteria criteria=indentExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        indentExample.setOrderByClause("create_time desc");
        Indent indent=indentMapper.selectByExample(indentExample).get(0);
        if(indent!=null) return indent;
        return null;
    }
    
}

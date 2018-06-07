package cn.mh.persist.mapper;

import cn.mh.entity.ReturnMain;
import cn.mh.persist.example.ReturnMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReturnMainMapper {
    long countByExample(ReturnMainExample example);

    int deleteByExample(ReturnMainExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReturnMain record);

    int insertSelective(ReturnMain record);

    List<ReturnMain> selectByExample(ReturnMainExample example);

    ReturnMain selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReturnMain record, @Param("example") ReturnMainExample example);

    int updateByExample(@Param("record") ReturnMain record, @Param("example") ReturnMainExample example);

    int updateByPrimaryKeySelective(ReturnMain record);

    int updateByPrimaryKey(ReturnMain record);
}
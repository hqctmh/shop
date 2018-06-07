package cn.mh.persist.mapper;

import cn.mh.entity.ReturnView;
import cn.mh.persist.example.ReturnViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReturnViewMapper {
    long countByExample(ReturnViewExample example);

    int deleteByExample(ReturnViewExample example);

    int insert(ReturnView record);

    int insertSelective(ReturnView record);

    List<ReturnView> selectByExample(ReturnViewExample example);

    int updateByExampleSelective(@Param("record") ReturnView record, @Param("example") ReturnViewExample example);

    int updateByExample(@Param("record") ReturnView record, @Param("example") ReturnViewExample example);
}
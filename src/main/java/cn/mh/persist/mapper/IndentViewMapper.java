package cn.mh.persist.mapper;

import cn.mh.entity.IndentView;
import cn.mh.persist.example.IndentViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndentViewMapper {
    long countByExample(IndentViewExample example);

    int deleteByExample(IndentViewExample example);

    int insert(IndentView record);

    int insertSelective(IndentView record);

    List<IndentView> selectByExample(IndentViewExample example);

    int updateByExampleSelective(@Param("record") IndentView record, @Param("example") IndentViewExample example);

    int updateByExample(@Param("record") IndentView record, @Param("example") IndentViewExample example);
}
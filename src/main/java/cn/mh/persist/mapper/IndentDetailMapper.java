package cn.mh.persist.mapper;

import cn.mh.entity.IndentDetail;
import cn.mh.persist.example.IndentDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndentDetailMapper {
    long countByExample(IndentDetailExample example);

    int deleteByExample(IndentDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(IndentDetail record);

    int insertBatch(List<IndentDetail> indentDetailList);

    int insertSelective(IndentDetail record);

    List<IndentDetail> selectByExample(IndentDetailExample example);

    IndentDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") IndentDetail record, @Param("example") IndentDetailExample example);

    int updateByExample(@Param("record") IndentDetail record, @Param("example") IndentDetailExample example);

    int updateByPrimaryKeySelective(IndentDetail record);

    int updateByPrimaryKey(IndentDetail record);
}
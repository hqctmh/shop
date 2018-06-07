package cn.mh.persist.mapper;

import cn.mh.entity.ReturnDetail;
import cn.mh.persist.example.ReturnDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReturnDetailMapper {
    long countByExample(ReturnDetailExample example);

    int deleteByExample(ReturnDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReturnDetail record);

    int insertBatch(List<ReturnDetail> list);

    int insertSelective(ReturnDetail record);

    List<ReturnDetail> selectByExample(ReturnDetailExample example);

    ReturnDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReturnDetail record, @Param("example") ReturnDetailExample example);

    int updateByExample(@Param("record") ReturnDetail record, @Param("example") ReturnDetailExample example);

    int updateByPrimaryKeySelective(ReturnDetail record);

    int updateByPrimaryKey(ReturnDetail record);
}
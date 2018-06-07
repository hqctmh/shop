package cn.mh.persist.mapper;

import cn.mh.entity.StoreApply;
import cn.mh.persist.example.StoreApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreApplyMapper {
    long countByExample(StoreApplyExample example);

    int deleteByExample(StoreApplyExample example);

    int deleteByPrimaryKey(String id);

    int insert(StoreApply record);

    int insertSelective(StoreApply record);

    List<StoreApply> selectByExampleWithBLOBs(StoreApplyExample example);

    List<StoreApply> selectByExample(StoreApplyExample example);

    StoreApply selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StoreApply record, @Param("example") StoreApplyExample example);

    int updateByExampleWithBLOBs(@Param("record") StoreApply record, @Param("example") StoreApplyExample example);

    int updateByExample(@Param("record") StoreApply record, @Param("example") StoreApplyExample example);

    int updateByPrimaryKeySelective(StoreApply record);

    int updateByPrimaryKeyWithBLOBs(StoreApply record);

    int updateByPrimaryKey(StoreApply record);
}
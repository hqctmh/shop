package cn.mh.persist.mapper;

import cn.mh.entity.StoreIndent;
import cn.mh.persist.example.StoreIndentExample;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreIndentMapper {
    long countByExample(StoreIndentExample example);

    int deleteByExample(StoreIndentExample example);

    int deleteByPrimaryKey(String id);

    int insert(StoreIndent record);

    int insertBatch(List<StoreIndent> storeIndentList);

    int updateBatch(List<StoreIndent> storeIndentList);

    int insertSelective(StoreIndent record);

    List<StoreIndent> selectByExample(StoreIndentExample example);

    BigDecimal sumPrice(StoreIndentExample example);

    StoreIndent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StoreIndent record, @Param("example") StoreIndentExample example);

    int updateByExample(@Param("record") StoreIndent record, @Param("example") StoreIndentExample example);

    int updateByPrimaryKeySelective(StoreIndent record);

    int updateByPrimaryKey(StoreIndent record);
}
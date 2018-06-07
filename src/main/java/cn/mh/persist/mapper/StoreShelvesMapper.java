package cn.mh.persist.mapper;

import cn.mh.entity.StoreShelves;
import cn.mh.persist.example.StoreShelvesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreShelvesMapper {
    long countByExample(StoreShelvesExample example);

    int deleteByExample(StoreShelvesExample example);

    int deleteByPrimaryKey(String id);

    int insert(StoreShelves record);

    int insertSelective(StoreShelves record);

    List<StoreShelves> selectByExample(StoreShelvesExample example);

    StoreShelves selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StoreShelves record, @Param("example") StoreShelvesExample example);

    int updateByExample(@Param("record") StoreShelves record, @Param("example") StoreShelvesExample example);

    int updateByPrimaryKeySelective(StoreShelves record);

    int updateByPrimaryKey(StoreShelves record);
}
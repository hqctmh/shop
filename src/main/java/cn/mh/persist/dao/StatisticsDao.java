package cn.mh.persist.dao;


import cn.mh.entity.Statistics;
import cn.mh.persist.example.StatisticsExample;
import cn.mh.persist.mapper.StatisticsMapper;
import com.alibaba.druid.support.spring.stat.annotation.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatisticsDao {
    @Autowired
    private StatisticsMapper statisticsMapper;

    public boolean addStatistics(Statistics statistics){
        return statisticsMapper.insert(statistics)>0;
    }
    public boolean updateStatistics(Statistics statistics){
        return statisticsMapper.updateByPrimaryKeySelective(statistics)>0;
    }
    public boolean updateBatchStatistics(List<Statistics> list){
        return statisticsMapper.updateBatch(list)>0;
    }
    public Statistics findStatisticsById(String id){
        return statisticsMapper.selectByPrimaryKey(id);
    }

    public List<Statistics> findStatisticsByIdList(List<String> idList){
        StatisticsExample example=new StatisticsExample();
        example.createCriteria().andIdIn(idList);
        List<Statistics> list=statisticsMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;

    }
    public List<Statistics> findAllStatistics(String storeId){
        StatisticsExample example=new StatisticsExample();
        example.createCriteria().andStoreIdEqualTo(storeId);
        example.setOrderByClause("price desc");
        List<Statistics> list=statisticsMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
    public boolean delStatistics(String id){
        return statisticsMapper.deleteByPrimaryKey(id)>0;
    }
    
}

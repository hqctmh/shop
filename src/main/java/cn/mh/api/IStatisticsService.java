package cn.mh.api;

import cn.mh.entity.Statistics;
import cn.mh.util.ServiceResult;

public interface IStatisticsService {
    /**
     * 添加统计信息
     * @param statistics
     * @return
     */
    ServiceResult addStatistics(Statistics statistics);

    /**
     *修改统计信息
     * @param statistics
     * @return
     */
    ServiceResult updateStatistics(Statistics statistics);

    /**
     * 通过ID查询统计信息
     * @param id
     * @return
     */
    ServiceResult findStatistics(String id);

    /**
     * 删除统计信息
     * @param id
     * @return
     */
    ServiceResult delStatistics(String id);

    /**
     * 添加已有的商品统计
     * @return
     */
    ServiceResult addGoods();

    ServiceResult statisticsByAdmin();


}

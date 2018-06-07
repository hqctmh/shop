import cn.mh.api.IItemService;
import cn.mh.api.IStatisticsService;
import cn.mh.entity.Statistics;
import cn.mh.persist.mapper.StatisticsMapper;
import cn.mh.util.ServiceResult;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StatisticsTest extends BaseTest{
    @Autowired
    private IStatisticsService iStatisticsService;
    @Autowired
    private StatisticsMapper statisticsMapper;

    @Test
    public void TestAddGoods(){
        ServiceResult result=iStatisticsService.addGoods();
        if(result.succeed()){
            logger.info("result ----->"+ JSON.toJSONString(result));
        }
    }
    @Test
    public void TestUpdateBatch(){
        Statistics statistics1=new Statistics();
        statistics1.setId("03b80e19092b4dd2a9f2ee926c2c93f5");
        statistics1.setStoreId("120");
        statistics1.setPrice(new BigDecimal(0));
        statistics1.setAmount(123);
        Statistics statistics2=new Statistics();
        statistics2.setId("03c2941aa1384baeb4939cc58da2b2a9");
        statistics2.setStoreId("110");
        statistics2.setPrice(new BigDecimal(1));
        statistics2.setAmount(321);
        List<Statistics> list=new ArrayList<>();
        list.add(statistics1);
        list.add(statistics2);
        statisticsMapper.updateBatch(list);
    }
}

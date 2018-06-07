package cn.mh.service;

import cn.mh.api.IStatisticsService;
import cn.mh.entity.Goods;
import cn.mh.entity.Statistics;
import cn.mh.entity.Store;
import cn.mh.entity.model.AdminStatisticsModel;
import cn.mh.persist.dao.GoodsDao;
import cn.mh.persist.dao.StatisticsDao;
import cn.mh.persist.dao.StoreDao;
import cn.mh.persist.dao.StoreIndentDao;
import cn.mh.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service("statisticService")
public class StatisticServiceImpl extends BaseService implements IStatisticsService {
    @Autowired
    private StatisticsDao statisticsDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private StoreIndentDao storeIndentDao;

    @Override
    public ServiceResult addStatistics(Statistics statistics) {
        ServiceResult result=new ServiceResult();
        try{
            result= CheckParam.CheckEmptyObj(result,"id,门店ID",statistics.getId(),statistics.getStoreId());
            if(result.getCode()!=0){
                return result;
            }
            statistics.setPrice(new BigDecimal(0));
            statistics.setAmount(0);
            statisticsDao.addStatistics(statistics);
            result.setSucceed();
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult updateStatistics(Statistics statistics) {
        ServiceResult result=new ServiceResult();
        try{
            if(StringUtil.isBlank(statistics.getId())){
                result.setError(1,"id不得为空");
                return result;
            }
            statisticsDao.updateStatistics(statistics);
            result.setSucceed();
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult findStatistics(String id) {
        ServiceResult result=new ServiceResult();
        try{
            if(StringUtil.isBlank(id)){
                result.setError(1,"id不得为空");
            }
            Statistics statistics=statisticsDao.findStatisticsById(id);
            if(statistics!=null){
                result.setAttribute("result",statistics);
                result.setSucceed();
            }
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult delStatistics(String id) {
        ServiceResult result=new ServiceResult();
        try{
            if(StringUtil.isBlank(id)){
                result.setError(1,"id不得为空");
            }
            statisticsDao.delStatistics(id);
            result.setSucceed();
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult addGoods() {
        ServiceResult result=new ServiceResult();
        try{
            List<Goods> goodsList=goodsDao.findGoodsByLimit("","","","","0");
            for(Goods goods:goodsList){
                Statistics statistics=new Statistics();
                statistics.setId(goods.getId());
                statistics.setStoreId(goods.getStoreId());
                statistics.setAmount(0);
                statistics.setPrice(new BigDecimal(0));
                statisticsDao.addStatistics(statistics);
            }
            result.setSucceed();
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult statisticsByAdmin() {
        ServiceResult result=new ServiceResult();
        try{
            List<Store> storeList=storeDao.findStoreByLimit("","","");
            List<Date> list= DateUtil.getDayListFromOneWeek(new Date());
            List<AdminStatisticsModel> adminStatisticsModelList=new ArrayList<>();
            if(storeList!=null&&storeList.size()>0){
                for(Store store:storeList){
                    List<BigDecimal> priceList=new ArrayList<>();
                    for(Date date:list){
                        BigDecimal price=storeIndentDao.sumPrice(date,store.getId());
                        if(price==null){
                            price=new BigDecimal(0);
                        }
                        priceList.add(price);
                    }
                    AdminStatisticsModel model=new AdminStatisticsModel();
                    model.setName(store.getStoreName());
                    model.setPrice(priceList);
                    adminStatisticsModelList.add(model);
                }
                result.setAttribute("result",adminStatisticsModelList);
                result.setSucceed();
            }
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }
}

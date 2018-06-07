package cn.mh.service;

import cn.mh.api.IGoodsService;
import cn.mh.entity.Goods;
import cn.mh.entity.Item;
import cn.mh.entity.Statistics;
import cn.mh.persist.dao.GoodsDao;
import cn.mh.persist.dao.ItemDao;
import cn.mh.persist.dao.StatisticsDao;
import cn.mh.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("GoodsService")
@Transactional
public class GoodsServiceImpl extends BaseService implements IGoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private StatisticsDao statisticsDao;
    @Override
    public ServiceResult addGoods(Goods goods) {
        ServiceResult result = new ServiceResult();
        try {
            result = CheckParam.CheckEmptyObj(result, "父栏目ID，子栏目ID，门店ID，商品名称，价格，数量",
                    goods.getItemId(), goods.getSubitemId(), goods.getStoreId(), goods.getGoodsName(), goods.getPrice(),goods.getCount());
            if(result.getCode()!=0){
                return result;
            }
            goods.setId(UuidUtils.newid());
            goods.setCreateTime(new Date());
            goods.setDelFlag("0");
            if(StringUtil.isBlank(goods.getPic())){
                goods.setPic("nophoto");
            }
            goodsDao.addGoods(goods);
            Statistics statistics=new Statistics();
            statistics.setStoreId(goods.getStoreId());
            statistics.setAmount(0);
            statistics.setPrice(new BigDecimal(0));
            statistics.setId(goods.getId());
            statisticsDao.addStatistics(statistics);
            result.setSucceed();
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult updateGoods(Goods goods) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(goods.getId())){
                result.setError(1,"ID不得为空");
                return result;
            }
            goodsDao.updateGoods(goods);
            result.setSucceed();
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findGoodsById(String id) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(id)){
                result.setError(1,"ID不得为空");
                return result;
            }
            Goods goods=goodsDao.findGoodsById(id);
            if(goods!=null){
                result.setAttribute("result",goods);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult deleteGoodsById(String id) {
        return null;
    }

    @Override
    public ServiceResult findGoodsByLimit(String iid, String subId, String storeId, String name, String delFlag) {
        ServiceResult result = new ServiceResult();
        try {
            List<Goods> list=goodsDao.findGoodsByLimit(iid, subId, storeId, name, delFlag);
            if(list!=null&&list.size()>0){
                result.setAttribute("result",list);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findGoodsForPage(int pageNum, int pageSize, String iid, String subId, String storeId, String name, String delFlag) {
        ServiceResult result = new ServiceResult();
        try {
            Long count=goodsDao.getGoodsCount(iid, subId, storeId, name, delFlag);
            List<Goods> list=goodsDao.findGoodsForPage(result.createPage(count.intValue(),pageNum, pageSize),pageSize, iid, subId, storeId, name, delFlag);
            if(list!=null&&list.size()>0){
                result.setAttribute("result",list);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findGodsForIndex() {
        ServiceResult result = new ServiceResult();
        try {
            List<Item> itemList=itemDao.findItemByLimit("0","");
            if(itemList!=null&&itemList.size()>0) {
                Map<String, List<Goods>> goodsMap = new HashMap<>();
                for (Item item : itemList) {
                    List<Goods> goodsList = goodsDao.findGoodsForPage(0, 10, item.getId(), "", "", "", "0");
                    if(goodsList!=null&&goodsList.size()>0) {
                        goodsMap.put(item.getId(), goodsList);
                    }
                }
                result.setAttribute("result",goodsMap);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }
}

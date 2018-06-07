package cn.mh.service;

import cn.mh.api.IShopCarService;
import cn.mh.entity.Goods;
import cn.mh.entity.ShopCar;


import cn.mh.entity.Store;
import cn.mh.entity.model.ShopCarModel;
import cn.mh.persist.dao.*;
import cn.mh.util.*;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("ShopCarService")
@Transactional
public class ShopServiceImpl extends BaseService implements IShopCarService {
    @Autowired
    private ShopCarDao shopCarDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private StoreDao storeDao;
    @Override
    public ServiceResult addShopCar(ShopCar shopCar) {
        ServiceResult result = new ServiceResult();
        try {
            result=CheckParam.CheckEmptyObj(result,"用户ID，门店ID，商品ID，数量",
                    shopCar.getUserId(),shopCar.getStoreId(),shopCar.getGoodsId(),shopCar.getAccount());
            if(result.getCode()!=0){
                return result;
            }
            List<ShopCar> list=shopCarDao.findShopCarByLimit(shopCar.getUserId(),shopCar.getStoreId(),shopCar.getGoodsId());
            if(list!=null&&list.size()>0){
                shopCar=list.get(0);
                shopCar.setAccount(shopCar.getAccount()+1);
                shopCarDao.updateShopCar(shopCar);
            }else{
                shopCar.setId(UuidUtils.newid());
                shopCarDao.addShopCar(shopCar);
            }
            result.setSucceed();
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult updateShopCar(ShopCar shopCar) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(shopCar.getId())){
                result.setError(1,"ID不得为空");
            }
            shopCarDao.updateShopCar(shopCar);
            result.setSucceed();
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findShopCarById(String id) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(id)){
                result.setError(1,"ID不得为空");
            }
            ShopCar shopCar=shopCarDao.findShopCarById(id);
            if(shopCar!=null){
                result.setAttribute("result",shopCar);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult deleteShopCarById(String id) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(id)){
                result.setError(1,"ID不得为空");
            }
            shopCarDao.delShopCar(id);
            result.setSucceed();
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findShopCarByLimit(String userId, String storeId,String goodsId) {
        ServiceResult result = new ServiceResult();
        try {
            List<ShopCar> shopCarList=shopCarDao.findShopCarByLimit(userId, storeId,goodsId);
            if(shopCarList!=null&&shopCarList.size()>0){
                result.setAttribute("result",shopCarList);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findShopCarByUserId(String userId) {
        ServiceResult result = new ServiceResult();
        try {
            List<ShopCar> list=shopCarDao.findShopCarByLimit(userId, "","");
            if(list!=null&&list.size()>0){
                Map<String,List<ShopCarModel>> map=new HashMap<>();
                for(ShopCar shopCar:list){
                    if(map.get(shopCar.getStoreId())==null){
                        map.put(shopCar.getStoreId(),new ArrayList<ShopCarModel>());
                        ShopCarModel model=JSON.parseObject(JSON.toJSONString(shopCar),ShopCarModel.class);
                        Goods goods=goodsDao.findGoodsById(shopCar.getGoodsId());
                        Store store=storeDao.findStoreById(shopCar.getStoreId());
                        model.setGoodsName(goods.getGoodsName());
                        model.setPrice(goods.getPrice());
                        model.setStoreName(store.getStoreName());
                        model.setPic(goods.getPic());
                        model.setAllPrice(model.getPrice().multiply(new BigDecimal(model.getAccount())));
                        map.get(shopCar.getStoreId()).add(model);
                    }else{
                        ShopCarModel model=JSON.parseObject(JSON.toJSONString(shopCar),ShopCarModel.class);
                        Goods goods=goodsDao.findGoodsById(shopCar.getGoodsId());
                        Store store=storeDao.findStoreById(shopCar.getStoreId());
                        model.setGoodsName(goods.getGoodsName());
                        model.setPrice(goods.getPrice());
                        model.setStoreName(store.getStoreName());
                        model.setPic(goods.getPic());
                        model.setAllPrice(model.getPrice().multiply(new BigDecimal(model.getAccount())));
                        map.get(shopCar.getStoreId()).add(model);
                    }
                }
                result.setAttribute("result",map);
                result.setSucceed();
            }


        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }
}

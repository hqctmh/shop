package cn.mh.service;

import cn.mh.api.IStoreService;
import cn.mh.entity.Goods;
import cn.mh.entity.Store;
import cn.mh.entity.User;
import cn.mh.entity.model.StoreModel;
import cn.mh.persist.dao.GoodsDao;
import cn.mh.persist.dao.StoreDao;
import cn.mh.persist.dao.UserDao;
import cn.mh.util.BaseService;
import cn.mh.util.ServiceResult;
import cn.mh.util.StringUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("StoreService")
public class StoreServiceImpl extends BaseService implements IStoreService{
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public ServiceResult addStore(Store store) {
        ServiceResult result = new ServiceResult();
        try {

        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult updateStore(Store store) {
        return null;
    }

    @Override
    public ServiceResult findStoreById(String id) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(id)){
                result.setError(1,"门店ID不得为空");
                return result;
            }
            Store store=storeDao.findStoreById(id);
            if(store!=null){
                result.setAttribute("result",store);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult deleteStoreById(String id) {
        return null;
    }

    @Override
    public ServiceResult findStoreByLimit(String userId, String storeName, String status) {
        ServiceResult result = new ServiceResult();
        try {
            List<Store> list=storeDao.findStoreByLimit(userId, storeName, status);
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
    public ServiceResult findStoreForPage(int pageNum, int pageSize, String userId, String storeName, String status) {
        ServiceResult result = new ServiceResult();
        try {
            Long count=storeDao.getStoreCount(userId, storeName, status);
            List<Store> list=storeDao.findStoreForPage(result.createPage(count.intValue(),pageNum, pageSize),pageSize, userId, storeName, status);
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
    public ServiceResult findStoreModel(String storeName) {
        ServiceResult result=new ServiceResult();
        try{
            List<Store> storeList=storeDao.findStoreByLimit("",storeName,"");
            List<StoreModel> modelList=new ArrayList<>();
            for(Store store:storeList){
                StoreModel model= JSON.parseObject(JSON.toJSONString(store),StoreModel.class);
                User user=userDao.findUserById(store.getUserId());
                model.setUserName(user.getName());
                modelList.add(model);
            }
            result.setAttribute("result",modelList);
            result.setSucceed();
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult lockStore(String storeId) {
        ServiceResult result=new ServiceResult();
        try{
            Store store=storeDao.findStoreById(storeId);
            if(StringUtil.equals(store.getStatus(),"0")){
                store.setStatus("1");
                storeDao.updateStore(store);
                List<Goods> goodsList=goodsDao.findGoodsByLimit("","",storeId,"","");
                if(goodsList!=null&&goodsList.size()>0){
                    for(Goods goods:goodsList){
                        goods.setDelFlag("1");
                        goodsDao.updateGoods(goods);
                    }
                }
            }else{
                store.setStatus("0");
                storeDao.updateStore(store);
                List<Goods> goodsList=goodsDao.findGoodsByLimit("","",storeId,"","");
                if(goodsList!=null&&goodsList.size()>0){
                    for(Goods goods:goodsList){
                        goods.setDelFlag("0");
                        goodsDao.updateGoods(goods);
                    }
                }
            }
            result.setSucceed();
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }
}

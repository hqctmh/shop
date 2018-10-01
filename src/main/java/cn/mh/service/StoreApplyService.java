package cn.mh.service;

import cn.mh.api.IStoreApplyService;
import cn.mh.api.IStoreService;
import cn.mh.entity.Store;
import cn.mh.entity.StoreApply;
import cn.mh.entity.User;
import cn.mh.persist.dao.StoreApplyDao;
import cn.mh.persist.dao.StoreDao;
import cn.mh.persist.dao.UserDao;
import cn.mh.util.BaseService;
import cn.mh.util.ServiceResult;
import cn.mh.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("storeApplyService")
public class StoreApplyService extends BaseService implements IStoreApplyService {
    @Autowired
    private StoreApplyDao storeApplyDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private UserDao userDao;

    @Override
    public ServiceResult addStoreApply(String userId, String storeName) {
        ServiceResult result=new ServiceResult();
        try{
            StoreApply storeApply=new StoreApply();
            storeApply.setId(UuidUtils.newid());
            storeApply.setStoreName(storeName);
            storeApply.setUserId(userId);
            storeApply.setStatus("0");
            storeApplyDao.addStoreApply(storeApply);
            result.setSucceed();
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult agreeApply(String applyId) {
        ServiceResult result=new ServiceResult();
        try{
            StoreApply apply=storeApplyDao.findStoreApplyById(applyId);
            apply.setStatus("1");
            storeApplyDao.updateStoreApply(apply);

            Store store=new Store();
            store .setId(UuidUtils.newid());
            store.setUserId(apply.getUserId());
            store.setStoreName(apply.getStoreName());
            store.setBalance(new BigDecimal(0));
            store.setCreateTime(new Date());
            store.setStatus("0");
            storeDao.addStore(store);

            User user=userDao.findUserById(apply.getUserId());
            user.setUserType("1");
            userDao.update(user);
            result.setSucceed();
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult disAgreeApply(String applyId) {
        ServiceResult result=new ServiceResult();
        try{
            StoreApply apply=storeApplyDao.findStoreApplyById(applyId);
            apply.setStatus("2");
            storeApplyDao.updateStoreApply(apply);
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult findApply(String userId,String status) {
        ServiceResult result=new ServiceResult();
        try{
            List<StoreApply> applyList=storeApplyDao.findStoreApplyByLimit(userId,status);
            if(applyList!=null&&applyList.size()>0){
                result.setAttribute("result",applyList);
                result.setSucceed();
            }
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult findApplyByAdmin() {
        ServiceResult result=new ServiceResult();
        try{
            List<StoreApply> applyList=storeApplyDao.findStoreApplyByLimit("","0");
            List<Map<String,String>> mapList=new ArrayList<>();
            if(applyList!=null&&applyList.size()>0){
                for(StoreApply storeApply:applyList){
                    User user=userDao.findUserById(storeApply.getUserId());
                    Map<String,String> map=new HashMap<>();
                    map.put("userName",user.getName());
                    map.put("storeName",storeApply.getStoreName());
                    map.put("applyId",storeApply.getId());
                    mapList.add(map);
                }
                result.setAttribute("result",mapList);
                result.setSucceed();
            }
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }
}

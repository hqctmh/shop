package cn.mh.service;

import cn.mh.api.IUserService;
import cn.mh.entity.User;
import cn.mh.persist.dao.UserDao;
import cn.mh.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Transactional
@Service("UserService")
public class UserServiceImpl extends BaseService implements IUserService{
    @Autowired
    public UserDao userDao;
    public ServiceResult addUser(User user) {
        ServiceResult result = new ServiceResult();
        try {
            result= CheckParam.CheckEmptyObj(result,"姓名，密码，手机号",user.getName(),user.getPassword(),user.getPhone());
            if(result.getCode()!=0){
                return result;
            }
            user.setId(UuidUtils.newid());
            user.setPassword(Md5Util.getMD5(user.getPassword()));
            user.setBalance(new BigDecimal(0));
            user.setCreateTime(new Date());
            user.setUpdateTime(user.getCreateTime());
            user.setUserType("0");
            user.setStatus("0");
            if(userDao.add(user)){
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    public ServiceResult updateUser(User user) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(user.getId())){
                return result;
            }
            if(userDao.update(user)){
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;

    }

    public ServiceResult findUserById(String id) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(id)){
                return result;
            }
            User user=userDao.findUserById(id);
            if(user!=null){
                result.setAttribute("result",user);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    public ServiceResult deleteUserById(String id) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(id)){
                return result;
            }
            userDao.delete(id);
            result.setSucceed();
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult registUser(User user) {
        ServiceResult result = new ServiceResult();
        try {
            List<User> list=userDao.findByLimit(user.getPhone(),"","","");
            if(list!=null&&list.size()>0){
                result.setAttribute("message","该手机号已经被注册");
                return result;
            }
            ServiceResult sr=this.addUser(user);
            if(sr.succeed()){
                result.setSucceed();
            }else{
                result.setAttribute("message",sr.getMessage());
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult login(User user) {
        ServiceResult result = new ServiceResult();
        try {
            result=CheckParam.CheckEmptyObj(result,"手机号，密码",user.getPhone(),user.getPassword());
            if(result.getCode()!=0){
                return result;
            }
            user.setPassword(Md5Util.getMD5(user.getPassword()));
            List<User> list=userDao.findByLimit(user.getPhone(),user.getPassword(),"","");
            if(list!=null&&list.size()==1){
                result.setAttribute("result",list.get(0));
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult payPass(String userId, String psd) {
        ServiceResult result=new ServiceResult();
        try{
            User user=userDao.findUserById(userId);
            if(StringUtil.equals(user.getPassword(),Md5Util.getMD5(psd))){
                result.setSucceed();
            }
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult findAllUser() {
        ServiceResult result=new ServiceResult();
        try{
            List<User> userList=userDao.findByLimit("","","0","");
            if(userList!=null){
                result.setAttribute("result",userList);
                result.setSucceed();
            }
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult findUserByKey(String keyWord) {
        ServiceResult result=new ServiceResult();
        try{
            List<User> list=userDao.findByKeyWord(keyWord);
            if(list!=null&&list.size()>0){
                result.setAttribute("result",list);
                result.setSucceed();
            }
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }
}

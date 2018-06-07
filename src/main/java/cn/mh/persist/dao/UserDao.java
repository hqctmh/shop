package cn.mh.persist.dao;

import cn.mh.entity.User;
import cn.mh.persist.example.UserExample;
import cn.mh.persist.example.UserExample.Criteria;
import cn.mh.persist.mapper.UserMapper;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private UserMapper userMapper;

    public boolean add(User user){
        return userMapper.insert(user)>0;
    }
    public boolean update(User user){
        return userMapper.updateByPrimaryKey(user)>0;
    }
    public boolean delete(String id){
        return userMapper.deleteByPrimaryKey(id)>0;
    }
    public User findUserById(String id){
        return userMapper.selectByPrimaryKey(id);
    }
    public List<User> findByLimit(String phone,String passWord,String user_type,String status){
        UserExample example=new UserExample();
        Criteria criteria=example.createCriteria();
        if(StringUtil.isNotBlank(phone)){
            criteria.andPhoneEqualTo(phone);
        }
        if(StringUtil.isNotBlank(passWord)){
            criteria.andPasswordEqualTo(passWord);
        }
        if(StringUtil.isNotBlank(user_type)){
            criteria.andUserTypeEqualTo(user_type);
        }
        if(StringUtil.isNotBlank(status)){
            criteria.andStatusEqualTo(status);
        }
        List<User> list=userMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
    public List<User> findByKeyWord(String keyWord){
        UserExample example=new UserExample();
        Criteria criteria=example.createCriteria();
        criteria.andUserTypeEqualTo("0").andOrDemo("%"+keyWord+"%");
        List<User> list=userMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
}

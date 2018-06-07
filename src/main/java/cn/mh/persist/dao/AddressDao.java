package cn.mh.persist.dao;


import cn.mh.entity.Address;
import cn.mh.persist.example.AddressExample;
import cn.mh.persist.example.AddressExample.Criteria;
import cn.mh.persist.mapper.AddressMapper;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDao {
    @Autowired
    private AddressMapper addressMapper;

    public boolean addAddress(Address address){
        return addressMapper.insert(address)>0;
    }
    public boolean updateAddress(Address address){
        return addressMapper.updateByPrimaryKeySelective(address)>0;
    }
    public Address findAddressById(String id){
        return addressMapper.selectByPrimaryKey(id);
    }
    public boolean delAddress(String id){
        return addressMapper.deleteByPrimaryKey(id)>0;
    }

    public List<Address> findAddressByLimit(String userId,String status){
        AddressExample example=new AddressExample();
        Criteria criteria=example.createCriteria();
        staticSet(criteria,userId, status);
        example.setOrderByClause("status desc");
        List<Address> list=addressMapper.selectByExample(example);
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }




    private static void  staticSet(Criteria criteria,String userId,String status){
        if(StringUtil.isNotBlank(userId)){
            criteria.andUserIdEqualTo(userId);
        }
        if(StringUtil.isNotBlank(status)){
            criteria.andStatusEqualTo(status);
        }

    }

}

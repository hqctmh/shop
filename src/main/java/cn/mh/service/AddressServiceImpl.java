package cn.mh.service;

import cn.mh.api.IAddressService;
import cn.mh.entity.Address;
import cn.mh.entity.City;
import cn.mh.entity.Province;
import cn.mh.entity.model.AddressModel;
import cn.mh.persist.dao.AddressDao;
import cn.mh.persist.dao.CityDao;
import cn.mh.persist.dao.ProvinceDao;
import cn.mh.util.*;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("AddressService")
@Transactional
public class AddressServiceImpl extends BaseService implements IAddressService {
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private ProvinceDao provinceDao;
    @Override
    public ServiceResult addAddress(Address address) {
        ServiceResult result = new ServiceResult();
        try {
            result = CheckParam.CheckEmptyObj(result, "用户ID，省份ID，城市ID，详细地址，手机号",
                    address.getUserId(), address.getProvinceId(), address.getCityId(), address.getAddress(), address.getPhone());
            if(result.getCode()!=0){
                return result;
            }
            address.setId(UuidUtils.newid());
            address.setCreateTime(new Date());
            List<Address> addressList=addressDao.findAddressByLimit(address.getUserId(),"1");
            if(address.getStatus()!=null){//新地址为默认地址
                address.setStatus("1");
                if(addressList!=null&&addressList.size()>0){
                    Address oldAddress=addressList.get(0);
                    oldAddress.setStatus("0");
                    addressDao.updateAddress(oldAddress);
                }
            }else{//新地址没有选默认地址
                if(addressList==null||addressList.size()==0){//第一次添加地址
                    address.setStatus("1");
                }else{//不是第一次添加地址
                    address.setStatus("0");
                }
            }
            addressDao.addAddress(address);
            result.setSucceed();
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult updateAddress(Address address) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(address.getId())){
                result.setError(1,"ID不得为空");
                return result;
            }
            addressDao.updateAddress(address);
            result.setSucceed();
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findAddressById(String id) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(id)){
                result.setError(1,"ID不得为空");
                return result;
            }
            Address address=addressDao.findAddressById(id);
            if(address!=null){
                result.setAttribute("result",address);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult deleteAddressById(String id) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(id)){
                result.setError(1,"ID不得为空");
                return result;
            }
            addressDao.delAddress(id);
            result.setSucceed();
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findAddressByLimit(String userId, String status) {
        ServiceResult result = new ServiceResult();
        try {
            List<Address> list=addressDao.findAddressByLimit(userId, status);
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
    public ServiceResult findAddressWithProvince(String userId, String status) {
        ServiceResult result = new ServiceResult();
        try {
            List<Address> list=addressDao.findAddressByLimit(userId, status);
            if(list!=null&&list.size()>0){
                List<AddressModel> modelList=new ArrayList<>();
                for(Address address:list){
                    AddressModel model= JSON.parseObject(JSON.toJSONString(address),AddressModel.class);
                    Province province=provinceDao.findProvinceById(model.getProvinceId());
                    City city=cityDao.findCityById(model.getCityId());
                    model.setCityName(city.getTitle());
                    model.setProvinceName(province.getTitle());
                    modelList.add(model);
                }
                result.setAttribute("result",modelList);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }
}

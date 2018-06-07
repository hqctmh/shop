package cn.mh.api;

import cn.mh.entity.Address;
import cn.mh.util.ServiceResult;

public interface IAddressService {
    /**
     * 添加地址
     * @param address
     * @return
     */
    ServiceResult addAddress(Address address);

    /**
     * 修改地址
     * @param address
     * @return
     */
    ServiceResult updateAddress(Address address);

    /**
     * 通过ID查找地址
     * @param id
     * @return
     */
    ServiceResult findAddressById(String id);

    /**
     * 删除地址
     * @param id
     * @return
     */
    ServiceResult deleteAddressById(String id);

    /**
     * 根据条件查询
     * @param userId
     * @param status
     * @return
     */
    ServiceResult findAddressByLimit(String userId,String status);

    /**
     * 条件查询并附带城市以及省份
     * @param userId
     * @param status
     * @return
     */
    ServiceResult findAddressWithProvince(String userId,String status);


}

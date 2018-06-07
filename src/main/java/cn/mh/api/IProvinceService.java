package cn.mh.api;

import cn.mh.entity.Province;
import cn.mh.util.ServiceResult;

public interface IProvinceService {

    /**
     * 通过ID查找省份
     * @param id
     * @return
     */
    ServiceResult findProvinceById(String id);

    /**
     * 查询出所有省份
     * @return
     */
    ServiceResult findAllProvince();
}

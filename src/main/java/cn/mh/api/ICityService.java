package cn.mh.api;

import cn.mh.util.ServiceResult;

public interface ICityService {
    /**
     * 查询城市
     * @param id
     * @return
     */
    ServiceResult findCityById(String id);

    /**
     * 根据省份ID查询城市
     * @param provinceId
     * @return
     */
    ServiceResult findCityByProvinceId(String provinceId);
}

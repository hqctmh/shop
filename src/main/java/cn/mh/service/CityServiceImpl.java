package cn.mh.service;

import cn.mh.api.ICityService;
import cn.mh.entity.City;
import cn.mh.persist.dao.CityDao;
import cn.mh.util.BaseService;
import cn.mh.util.ServiceResult;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CityService")
@Transactional
public class CityServiceImpl extends BaseService implements ICityService {
    @Autowired
    private CityDao cityDao;
    @Override
    public ServiceResult findCityById(String id) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(id)){
                result.setError(1,"ID不得为空");
                return result;
            }
            City city=cityDao.findCityById(id);
            if(city!=null){
                result.setAttribute("result",city);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findCityByProvinceId(String provinceId) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(provinceId)){
                result.setError(1,"ID不得为空");
                return result;
            }
            List<City> cityList=cityDao.findCityByLimit(provinceId);
            if(cityList!=null&&cityList.size()>0){
                result.setAttribute("result",cityList);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }
}

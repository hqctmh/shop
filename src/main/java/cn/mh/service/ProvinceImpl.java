package cn.mh.service;

import cn.mh.api.IProvinceService;
import cn.mh.entity.Province;
import cn.mh.persist.dao.ProvinceDao;
import cn.mh.util.BaseService;
import cn.mh.util.ServiceResult;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Province")
@Transactional
public class ProvinceImpl extends BaseService implements IProvinceService {
    @Autowired
    private ProvinceDao provinceDao;

    @Override
    public ServiceResult findProvinceById(String id) {
        ServiceResult result = new ServiceResult();
        try {
            if(StringUtil.isBlank(id)){
                result.setError(1,"ID不得为空");
                return result;
            }
            Province province=provinceDao.findProvinceById(id);
            if(province!=null){
                result.setAttribute("result",province);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findAllProvince() {
        ServiceResult result = new ServiceResult();
        try {
            result.setAttribute("result",provinceDao.findAllProvince());
            result.setSucceed();
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }
}

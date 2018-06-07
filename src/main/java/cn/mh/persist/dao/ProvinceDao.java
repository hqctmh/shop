package cn.mh.persist.dao;


import cn.mh.entity.Province;
import cn.mh.persist.example.ProvinceExample;
import cn.mh.persist.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProvinceDao {
    @Autowired
    private ProvinceMapper provinceMapper;

    public boolean addProvince(Province province){
        return provinceMapper.insert(province)>0;
    }
    public boolean updateProvince(Province province){
        return provinceMapper.updateByPrimaryKeySelective(province)>0;
    }
    public Province findProvinceById(String id){
        return provinceMapper.selectByPrimaryKey(id);
    }
    public boolean delProvince(String id){
        return provinceMapper.deleteByPrimaryKey(id)>0;
    }

    public List<Province> findAllProvince(){
        return provinceMapper.selectByExample(new ProvinceExample());
    }
    

}

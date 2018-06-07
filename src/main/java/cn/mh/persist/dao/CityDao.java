package cn.mh.persist.dao;


import cn.mh.entity.City;
import cn.mh.persist.example.CityExample;

import cn.mh.persist.mapper.CityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityDao {
    @Autowired
    private CityMapper cityMapper;

    public boolean addCity(City city){
        return cityMapper.insert(city)>0;
    }
    public boolean updateCity(City city){
        return cityMapper.updateByPrimaryKeySelective(city)>0;
    }
    public City findCityById(String id){
        return cityMapper.selectByPrimaryKey(id);
    }
    public boolean delCity(String id){
        return cityMapper.deleteByPrimaryKey(id)>0;
    }

    public List<City> findCityByLimit(String provinceId){
        CityExample example=new CityExample();
        example.createCriteria().andProvinceIdEqualTo(provinceId);
        List<City> list=cityMapper.selectByExample(example);
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }


}

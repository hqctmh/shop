package cn.mh.controller;

import cn.mh.api.ICityService;
import cn.mh.controller.abs.AbstractController;
import cn.mh.entity.City;
import cn.mh.util.ServiceResult;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/city")
public class CityController extends AbstractController {
    @Autowired
    private ICityService iCityService;

    @ResponseBody
    @RequestMapping(value = "/findCityByPid",produces = "application/json; charset=utf-8")
    public String findCityByPid(String pid){
        System.out.println(pid);
        ServiceResult rs=iCityService.findCityByProvinceId(pid);
        if(rs.succeed())
        return JSON.toJSONString(rs.getList("result", City.class));
        return null;
    }

    @Override
    public String getSaveFileDiv() {
        return null;
    }
}

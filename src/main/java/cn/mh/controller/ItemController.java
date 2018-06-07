package cn.mh.controller;

import cn.mh.api.IItemService;
import cn.mh.controller.abs.AbstractController;
import cn.mh.entity.Item;
import cn.mh.util.ServiceResult;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/item")
public class ItemController extends AbstractController {
    @Autowired
    private IItemService iItemService;

    @RequestMapping(value = "/findItem", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findItem(){
        ServiceResult result=iItemService.findByLimit("0",null);
        if(result.succeed()){
            return JSON.toJSONString(result.getObject("result"));
        }
        return null;
    }
    @RequestMapping(value = "/findSubItem", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findSubItem(String iid){
        ServiceResult result=iItemService.findByLimit("1",iid);
        if(result.succeed()){
            return JSON.toJSONString(result.getObject("result"));
        }
        return null;
    }






    @Override
    public String getSaveFileDiv() {
        return null;
    }
}

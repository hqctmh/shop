package cn.mh.controller;

import cn.mh.api.IGoodsService;
import cn.mh.api.IIndentService;
import cn.mh.api.IItemService;
import cn.mh.controller.abs.AbstractController;
import cn.mh.entity.Goods;
import cn.mh.entity.Item;
import cn.mh.util.Md5Util;
import cn.mh.util.ServiceResult;
import cn.mh.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Goods/")
public class GoodsController extends AbstractController{
    @Autowired
    private IGoodsService iGoodsService;
    @Autowired
    private IItemService iItemService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IIndentService iIndentService;

    @ResponseBody
    @RequestMapping(value = "/findMyGoodsForPage",produces = "application/json; charset=utf-8")
    public String findMyGoodsForPage(int pageNum){
        ServiceResult rs=iGoodsService.findGoodsForPage(pageNum, 7,"","",this.getStoreId(session),"","0");
        if(rs.succeed()){
            return JSON.toJSONString(rs.getObject("result"));
        }
        return null;
    }
    @ResponseBody
    @RequestMapping(value = "/findGoodsForPage",produces = "application/json; charset=utf-8")
    public String findGoodsForPage(int pageNum){
        String iid=request.getParameter("iid");
        String subId=request.getParameter("subId");
        String goodsName=request.getParameter("goodsName");
        if(StringUtil.equals("null",iid)){
            iid="";
        }
        if(StringUtil.equals("null",subId)){
            subId="";
        }
        if(StringUtil.equals("null",goodsName)){
            goodsName="";
        }
        System.out.println(iid);
        System.out.println(subId);
        ServiceResult rs=iGoodsService.findGoodsForPage(pageNum, 10,iid,subId,"",goodsName,"0");
        if(rs.succeed()){
            return JSON.toJSONString(rs.getObject("result"));
        }
        return null;
    }



    @RequestMapping("allGoods")
    public String allGoods(Model model){
        String iid=request.getParameter("iid");
        String subId=request.getParameter("subId");
        String goodsName=request.getParameter("goodsName");
        itemList(model, iItemService);
        ServiceResult rs=iGoodsService.findGoodsForPage(1,10,iid,subId,"",goodsName,"0");
        if(!rs.succeed()){
            model.addAttribute("msg", "您所搜索的产品为空");
            model.addAttribute("url", "");
            return "forward";
        }
        if(rs.succeed()){
            model.addAttribute("goodsList",rs.getList("result", Goods.class));
            model.addAttribute("count",rs.getRecordCount());
            model.addAttribute("iid",iid==null?"null":iid);
            model.addAttribute("subId",subId==null?"null":subId);
            model.addAttribute("goodsName",goodsName==null?"null":goodsName);
            model.addAttribute("active","allGoods");
        }
        if(StringUtil.isNotBlank(iid)){
            rs=iItemService.findById(iid);
            if(rs.succeed()){
                model.addAttribute("item",rs.getObject("result"));
            }
        }
        if(StringUtil.isNotBlank(subId)){
            rs=iItemService.findItemBySubItem(subId);
            if(rs.succeed()){
                JSONObject obj=JSON.parseObject(JSON.toJSONString(rs.getObject("result")));
                model.addAttribute("item",obj.get("item"));
                model.addAttribute("subItem",obj.get("subItem"));
            }
        }


        return "front/goods/all_goods";
    }

    @RequestMapping("goodsDetail")
    public String goodsDetail(Model model,String goodsId){
        itemList(model, iItemService);
        ServiceResult rs=iGoodsService.findGoodsById(goodsId);
        if(rs.succeed()){
            model.addAttribute("goods",rs.getObject("result"));
        }
        rs=iIndentService.findGoodsMessage(goodsId);
        if(rs.succeed()){
            model.addAttribute("message",rs.getObject("result"));
        }
        return "front/goods/goods_detail";
    }


    @Override
    public String getSaveFileDiv() {
        return null;
    }


}

package cn.mh.controller;

import cn.mh.api.*;
import cn.mh.controller.abs.AbstractController;
import cn.mh.entity.*;
import cn.mh.entity.model.AddressModel;
import cn.mh.util.CheckContentType;
import cn.mh.util.ServiceResult;
import cn.mh.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pages/front/member/")
public class MemberController extends AbstractController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IItemService iItemService;
    @Autowired
    private IGoodsService iGoodsService;
    @Autowired
    private IAddressService iAddressService;
    @Autowired
    private IProvinceService iProvinceService;
    @Autowired
    private ICityService iCityService;
    @Autowired
    private IShopCarService iShopCarService;
    @Autowired
    private IIndentService iIndentService;
    @Autowired
    private IStoreApplyService iStoreApplyService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;


    public static final Logger logger = Logger.getLogger(MemberController.class);

    @RequestMapping("/myIndex")
    public String myIndex(Model model) {
        model.addAttribute("active", "my_index");
        itemList(model, iItemService);
        ServiceResult rs=iUserService.findUserById(super.getUserId(session));
        session.setAttribute("user",JSON.parseObject(JSON.toJSONString(rs.getObject("result")),User.class));
        return "front/member/my_index";
    }

    @RequestMapping("/addGoodsPro")
    public String addGoodsPro(Model model) {
        itemList(model, iItemService);
        ServiceResult result = iItemService.findByLimit("0", "");
        if (result.succeed()) {
            model.addAttribute("item", result.getObject("result"));
        }
        model.addAttribute("active", "add_goods");
        return "front/member/add_goods";
    }

    @RequestMapping("/addGoods")
    public String addGoods(Model model, Goods goods, MultipartFile photo) {
        if (photo == null) {
            model.addAttribute("msg", "请上传图片!");
            model.addAttribute("url", "/pages/front/member/addGoodsPro");
            return "forward";
        }
        if (!CheckContentType.checkType(photo.getContentType())) {
            model.addAttribute("msg", "上传文件必须为图片!");
            model.addAttribute("url", "/pages/front/member/addGoodsPro");
            return "forward";
        }
        goods.setPic(this.createSingleFileName(photo));
        this.saveUploadFile(photo, request, goods.getPic());
        goods.setStoreId(((Store) session.getAttribute("store")).getId());
        ServiceResult result = iGoodsService.addGoods(goods);
        if (result.succeed()) {
            model.addAttribute("msg", "添加成功！");
            model.addAttribute("url", "/pages/front/member/addGoodsPro");
        } else {
            model.addAttribute("msg", result.getMessage());
            model.addAttribute("url", "/pages/front/member/addGoodsPro");
        }
        return "forward";
    }

    @RequestMapping("/myGoods")
    public String myGoods(Model model) {
        itemList(model, iItemService);
        ServiceResult rs = iGoodsService.findGoodsForPage(1, 7, "", "", this.getStoreId(session), "", "0");
        if (rs.succeed()) {
            model.addAttribute("goodsList", rs.getObject("result"));
            model.addAttribute("count", rs.getRecordCount());
        }
        return "front/member/my_goods";
    }

    @ResponseBody
    @RequestMapping("/updateGoods")
    public String updateGoods(Goods goods) {
        ServiceResult rs = iGoodsService.updateGoods(goods);
        if (rs.succeed()) {
            return "true";
        } else {
            return "false";
        }
    }


    @RequestMapping("/myAddress")
    public String myAddress(Model model) {
        model.addAttribute("active", "my_address");
        itemList(model, iItemService);
        ServiceResult rs = iAddressService.findAddressWithProvince(getUserId(session), "");
        if (rs.succeed()) {
            model.addAttribute("addressList", rs.getObject("result"));
        }
        return "front/member/my_address";
    }

    @RequestMapping("/addAddressPro")
    public String addAddressPro(Model model) {
        model.addAttribute("active", "my_address");
        itemList(model, iItemService);
        ServiceResult rs = iProvinceService.findAllProvince();
        if (rs.succeed()) {
            model.addAttribute("provinceList", rs.getObject("result"));
        }
        return "front/member/add_address";
    }

    @RequestMapping("/addAddress")
    public String addAddress(Model model, Address address) {
        model.addAttribute("active", "my_address");
        address.setUserId(getUserId(session));
        ServiceResult rs = iAddressService.addAddress(address);
        if (rs.succeed()) {
            model.addAttribute("msg", "添加成功");
            model.addAttribute("url", "/pages/front/member/myAddress");
        } else {
            model.addAttribute("msg", rs.getMessage());
            model.addAttribute("url", "/pages/front/member/addAddressPro");
        }
        return "forward";
    }

    @RequestMapping("/updateAddressPro")
    public String updateAddressPro(String id, Model model) {
        itemList(model, iItemService);
        model.addAttribute("active", "my_address");
        ServiceResult rs = iAddressService.findAddressById(id);
        Address address = JSON.parseObject(JSON.toJSONString(rs.getObject("result")), Address.class);
        if (rs.succeed()) {
            model.addAttribute("address", address);
        }
        rs = iProvinceService.findAllProvince();
        if (rs.succeed()) {
            model.addAttribute("provinceList", rs.getObject("result"));
        }
        rs = iCityService.findCityByProvinceId(address.getProvinceId());
        if (rs.succeed()) {
            model.addAttribute("cityList", rs.getObject("result"));
        }
        return "front/member/update_address";
    }

    @RequestMapping("updateAddress")
    public String updateAddress(Address address, Model model) {
        ServiceResult rs = iAddressService.updateAddress(address);
        if (rs.succeed()) {
            model.addAttribute("msg", "修改成功!");
            model.addAttribute("url", "/pages/front/member/myAddress");
        } else {
            model.addAttribute("msg", "修改失败！");
            model.addAttribute("url", "/pages/front/member/myAddress");
        }
        return "forward";
    }


    @RequestMapping("/delAddress")
    public String delAddress(String id, Model model) {
        ServiceResult rs = iAddressService.deleteAddressById(id);
        if (rs.succeed()) {
            model.addAttribute("msg", "删除成功！");
            model.addAttribute("url", "/pages/front/member/myAddress");
        } else {
            model.addAttribute("msg", "删除失败！");
            model.addAttribute("url", "/pages/front/member/myAddress");
        }
        return "forward";
    }

    @ResponseBody
    @RequestMapping(value = "/addShopCar", produces = "application/json; charset=utf-8")
    public String addShopCar(ShopCar shopCar) {
        shopCar.setUserId(getUserId(session));
        ServiceResult rs = iShopCarService.addShopCar(shopCar);
        if (rs.succeed()) {
            return "true";
        }
        return "false";
    }

    @RequestMapping("/myShopCar")
    public String findShopCar(Model model) {
        ServiceResult rs = iShopCarService.findShopCarByUserId(getUserId(session));
        if (rs.succeed()) {
            model.addAttribute("map", rs.getObject("result"));
        }
        return "front/member/my_shopcar";
    }

    @ResponseBody
    @RequestMapping("/delShopCar")
    public String delShopCar(String shopCarId) {
        ServiceResult rs = iShopCarService.deleteShopCarById(shopCarId);
        if (rs.succeed()) {
            return "true";
        }
        return "false";
    }

    @ResponseBody
    @RequestMapping("/updateShopCar")
    public String updateShopCar(ShopCar shopCar) {
        ServiceResult rs = iShopCarService.updateShopCar(shopCar);
        if (rs.succeed()) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping("createIndentPro")
    public String createIndentPro(String str, Model model) {
        System.out.println(str);
        String[] jsonArr = str.split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String item : jsonArr) {
            String goodsId = item.split(":")[0];
            String account = item.split(":")[1];
            map.put(goodsId, Integer.parseInt(account));
        }
        //logger.info(JSON.toJSONString(map));
        ServiceResult result = iIndentService.findGoodsForIndent(map);
        if (result.succeed()) {
            Map goodsMap = JSON.parseObject(JSON.toJSONString(result.getObject("goodsMap")));
            List<Store> storeList = result.getList("storeList", Store.class);
            model.addAttribute("goodsMap", goodsMap);
            model.addAttribute("storeList", storeList);
        }
        result = iAddressService.findAddressWithProvince(super.getUserId(session), "");
        if (result.succeed()) {
            model.addAttribute("address", result.getList("result", AddressModel.class));
        }

        return "front/member/my_indent";
    }

    @ResponseBody
    @RequestMapping("createIndent")
    public String createIndent(String json) {
        JSONObject jso = JSON.parseObject(json);
        String addressId = jso.getString("addressId");
        JSONArray goodsArray = jso.getJSONArray("goods");
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < goodsArray.size(); i++) {
            JSONObject goods = goodsArray.getJSONObject(i);
            map.put(goods.getString("goodsId"), goods.getInteger("count"));
        }
        ServiceResult rs = iIndentService.addIndent(map, super.getUserId(session), addressId);
        if (rs.succeed()) {
            return "true";
        }
        return "false";
    }

    @ResponseBody
    @RequestMapping(value = "pay", produces = "application/json; charset=utf-8")
    public String pay(String password) {
        String storeIndentId = (String) request.getAttribute("storeIndentId");
        List<String> list = null;
        if (StringUtil.isNotBlank(storeIndentId)) {
            list = new ArrayList<>();
            list.add(storeIndentId);
        }
        ServiceResult rs = iUserService.payPass(super.getUserId(session), password);
        JSONObject object = new JSONObject();
        if (rs.succeed()) {
            rs = iIndentService.payIndent(list, super.getUserId(session));
            if (rs.succeed()) {
                object.put("flag", true);
            } else {
                object.put("flag", false);
                object.put("message", rs.getMessage());
            }
        } else {
            object.put("flag", false);
            object.put("message", "支付密码错误");
        }

        return JSON.toJSONString(object);
    }

    @RequestMapping("payByBtnPro")
    public String payByBtnPro(Model model, String storeIndentId) {
        ServiceResult rs = iIndentService.findStoreIndentById(storeIndentId);
        if (rs.succeed()) {
            model.addAttribute("storeIndent", rs.getObject("result"));
        }
        return "front/member/pay_page";
    }

    @RequestMapping("payByBtn")
    public String payByBtn(Model model, String storeIndentId, String password) {
        ServiceResult rs = iUserService.payPass(super.getUserId(session), password);
        if (rs.succeed()) {
            List<String> list = new ArrayList<>();
            list.add(storeIndentId);
            rs = iIndentService.payIndent(list, super.getUserId(session));
            if (rs.succeed()) {
                model.addAttribute("msg", "支付成功！");
                model.addAttribute("url", "/pages/front/member/myAllOrder?type=myAllOrder");
            } else {
                model.addAttribute("msg", rs.getMessage());
                model.addAttribute("url", "/pages/front/member/myAllOrder?type=myAllOrder");
            }
        } else {
            model.addAttribute("msg", "支付密码错误！");
            model.addAttribute("url", "/pages/front/member/myAllOrder?type=myAllOrder");
        }
        return "forward";
    }

    @RequestMapping("myAllOrder")
    public String myAllOrder(Model model, String type) {
        itemList(model, iItemService);
        model.addAttribute("active", "my_order");
        model.addAttribute("orderActive", type);
        String status = null;
        switch (type) {
            case "myAllOrder":
                status = "0,1,2,3,4,5";
                break;
            case "shouldPayOrder":
                status = "0,";
                break;
            case "havePayOrder":
                status = "1,";
                break;
            case "shouldReceiveOrder":
                status = "2,";
                break;
            case "comOrder":
                status = "3,";
                break;
        }

        String userId = "";
        String storeId = "";
        User user = super.getUser(session);
        if (StringUtil.equals(user.getUserType(), "0")) {
            userId = user.getId();
        } else {
            storeId = super.getStoreId(session);
        }
        ServiceResult rs = iIndentService.findIndentGoodsForPage(1, 5, userId, storeId, status);
        if (rs.succeed()) {
            model.addAttribute("list", rs.getObject("result"));
            model.addAttribute("count", rs.getRecordCount());
        }
        return "front/member/my_all_order";
    }

    @ResponseBody
    @RequestMapping(value = "myAllOrderForPage", produces = "application/json; charset=utf-8")
    public String myAllOrderForPage(String type, int pageNum) {
        String status = null;
        switch (type) {
            case "myAllOrder":
                status = "0,1,2,3,4,5";
                break;
            case "shouldPayOrder":
                status = "0,";
                break;
            case "havePayOrder":
                status = "1,";
                break;
            case "shouldReceiveOrder":
                status = "2,";
                break;
            case "comOrder":
                status = "3,";
                break;
        }
        String userId = "";
        String storeId = "";
        User user = super.getUser(session);
        if (StringUtil.equals(user.getUserType(), "0")) {
            userId = user.getId();
        } else {
            storeId = super.getStoreId(session);
        }
        ServiceResult rs = iIndentService.findIndentGoodsForPage(pageNum, 5, userId, storeId, status);
        if (rs.succeed()) {
            return JSON.toJSONString(rs.getObject("result"));
        }
        return null;
    }

    @RequestMapping("updateStatus")
    public String updateIndentStatus(Model model, String storeIndentId, String status) {
        ServiceResult rs = iIndentService.updateStoreIndent(storeIndentId, status);
        if (rs.succeed()) {
            switch (status) {
                case "1":
                    model.addAttribute("msg", "付款成功!");
                    break;
                case "2":
                    model.addAttribute("msg", "发货成功!");
                    break;
                case "3":
                    model.addAttribute("msg", "确认收货成功!");
                    break;
                case "4":
                    model.addAttribute("msg", "取消订单成功!");
                    break;
                case "5":
                    rs = iIndentService.addReturnNote(storeIndentId);
                    if (rs.succeed()) {
                        model.addAttribute("msg", "申请退货成功！");
                    } else {
                        model.addAttribute("msg", "退货单生成失败！");
                    }
                    break;
            }
            model.addAttribute("url", "/pages/front/member/myAllOrder?type=myAllOrder");
        } else {
            model.addAttribute("msg", "订单状态修改成功！");
            model.addAttribute("url", "/pages/front/member/myAllOrder?type=myAllOrder");
        }
        return "forward";
    }

    @RequestMapping("findMyReturn")
    public String findMyReturn(Model model, String type) {
        itemList(model, iItemService);
        model.addAttribute("active", "my_return_order");
        model.addAttribute("orderActive", type);
        String userId = "";
        String storeId = "";
        User user = super.getUser(session);
        if (StringUtil.equals(user.getUserType(), "0")) {
            userId = user.getId();
        } else {
            storeId = super.getStoreId(session);
        }
        String status = null;
        switch (type) {
            case "all_return":
                status = "";
                break;
            case "apply_return":
                status = "0";
                break;
            case "agree_return":
                status = "1";
                break;
            case "do_not_agree_return":
                status = "2";
                break;
            case "send":
                status="3";
                break;
            case "com":
                status="4";
                break;
        }
        ServiceResult rs = iIndentService.findReturnDetailForPage(1, 5, "", storeId, userId, status);
        if(rs.succeed()){
            model.addAttribute("list",rs.getObject("result"));
            model.addAttribute("count",rs.getRecordCount());
        }
        return "front/member/my_all_return";
    }
    @ResponseBody
    @RequestMapping(value = "findMyReturnForPage", produces = "application/json; charset=utf-8")
    public String findMyReturnForPage(int pageNum,String type){
        String userId = "";
        String storeId = "";
        User user = super.getUser(session);
        if (StringUtil.equals(user.getUserType(), "0")) {
            userId = user.getId();
        } else {
            storeId = super.getStoreId(session);
        }
        String status = null;
        switch (type) {
            case "all_return":
                status = "";
                break;
            case "apply_return":
                status = "0";
                break;
            case "agree_return":
                status = "1";
                break;
            case "do_not_agree_return":
                status = "2";
                break;
            case "send":
                status="3";
                break;
            case "com":
                status="4";
                break;
        }
        ServiceResult rs = iIndentService.findReturnDetailForPage(pageNum, 5, "", storeId, userId, status);
        if(rs.succeed()){
            return JSON.toJSONString(rs.getObject("result"));
        }
        return null;
    }

    @RequestMapping("updateReturnStatus")
    public String updateReturnStatus(Model model, String returnMainId, String status) {
        ServiceResult rs = iIndentService.updateReturnMain(returnMainId, status);
        if (rs.succeed()) {
            switch (status) {
                case "1":
                    model.addAttribute("msg", "同意退货成功!");
                    break;
                case "2":
                    model.addAttribute("msg", "拒绝退货成功!");
                    break;
                case "3":
                    model.addAttribute("msg", "发货成功!");
                    break;
                case "4":
                    rs=iIndentService.returnGoods(returnMainId);
                    if(rs.succeed()) {
                        model.addAttribute("msg", "收货成功!");
                    }else{
                        model.addAttribute("msg", "收货失败!");
                    }
                    break;
            }
            model.addAttribute("url", "/pages/front/member/findMyReturn?type=all_return");
        } else {
            model.addAttribute("msg", "订单状态修改失败！");
            model.addAttribute("url", "/pages/front/member/findMyReturn?type=all_return");
        }
        return "forward";
    }
    @RequestMapping("myStatistics")
    public String myStatistics(Model model){
        itemList(model, iItemService);
        model.addAttribute("active", "statistics");
        ServiceResult rs=iIndentService.statisticsSell(getStoreId(session));
        if(rs.succeed()){
            model.addAttribute("list",rs.getObject("result"));
        }
        rs=iIndentService.statisticsSellGoods(getStoreId(session));
        if(rs.succeed()){
            model.addAttribute("GoodsList",rs.getObject("result"));
        }
        return "front/member/my_statistic";
    }
    @ResponseBody
    @RequestMapping("message")
    public String message(String goodsId,String msg){

        ServiceResult result=iIndentService.message(goodsId,super.getUserId(session),msg) ;
        if(result.succeed()){
            return "true";
        }
        return "false";
    }
    @RequestMapping("myMessage")
    public String myMessage(Model model){
        itemList(model, iItemService);
        ServiceResult result=iIndentService.myMessage(1,14,super.getStoreId(session));
        if(result.succeed()){
            model.addAttribute("count",result.getRecordCount());
            model.addAttribute("modelList",result.getObject("result"));
        }
        return "front/member/my_message";
    }
    @ResponseBody
    @RequestMapping(value = "myMessageForPage", produces = "application/json; charset=utf-8")
    public String myMessageForPage(int pageNum,Model model){
        ServiceResult result=iIndentService.myMessage(pageNum,15,super.getStoreId(session));
        if(result.succeed()){
            model.addAttribute("modelList",result.getObject("result"));
        }
        return JSON.toJSONString(result.getObject("result"));
    }
    @RequestMapping("registStorePro")
    public String registStorePro(Model model){
        itemList(model, iItemService);
        model.addAttribute("active","registStorePro");
        return "front/member/regist_store";
    }

    @RequestMapping("registStore")
    public String registStore(String storeName,Model model){

        ServiceResult rs=iStoreApplyService.addStoreApply(super.getUserId(session),storeName);
        if (rs.succeed()) {
            model.addAttribute("msg", "申请成功！");
            model.addAttribute("url", "/pages/front/member/myApply");
        } else {
            model.addAttribute("msg", "申请失败！");
            model.addAttribute("url", "");
        }
        return "forward";
    }

    @RequestMapping("myApply")
    public String myApply(Model model){
        itemList(model, iItemService);
        model.addAttribute("active","myApply");
        ServiceResult rs=iStoreApplyService.findApply(super.getUserId(session),"");
        if (rs.succeed()) {
           model.addAttribute("applyList",rs.getObject("result"));
        }
        return "front/member/my_Apply";
    }


    @Override
    public String getSaveFileDiv() {
        return "/resources/upload/goodsPic/";
    }

//    public static void main(String[] args) {
//        String json="{\"json\":[{\"storeId\":\"110\",\"goods\":[{\"goodsId\":\"0912ae7972fb47649204ef2f6e004a10\",\"count\":4},{\"goodsId\":\"2\",\"count\":3},{\"goodsId\":\"cead78bbb6e94eaaa0e00a1c7d7d2a9f\",\"count\":1},{\"goodsId\":\"f259a6dfb67848bcb7534402a8b07126\",\"count\":3},{\"goodsId\":\"1\",\"count\":1}]},{\"storeId\":\"120\",\"goods\":[{\"goodsId\":\"0912ae7972fb47649204ef2f6e004a10\",\"count\":4},{\"goodsId\":\"2\",\"count\":3},{\"goodsId\":\"cead78bbb6e94eaaa0e00a1c7d7d2a9f\",\"count\":1},{\"goodsId\":\"f259a6dfb67848bcb7534402a8b07126\",\"count\":3},{\"goodsId\":\"1\",\"count\":1}]},{\"addressId\":\"f81650ab34354dcdac8c84596fae9a98\"}]}";
//        JSONObject jso=JSON.parseObject(json);
//        JSON.p
//        JSONArray jsa=jso.getJSONArray("json");
//        int i=0;
//    }
}

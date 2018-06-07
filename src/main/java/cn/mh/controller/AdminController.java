package cn.mh.controller;

import cn.mh.api.IStatisticsService;
import cn.mh.api.IStoreService;
import cn.mh.api.IUserService;
import cn.mh.controller.abs.AbstractController;
import cn.mh.entity.User;
import cn.mh.util.ServiceResult;
import cn.mh.util.StringUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
@RequestMapping("/pages/back/admin/")
public class AdminController extends AbstractController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private HttpSession session;
    @Autowired
    private IStatisticsService iStatisticsService;
    @Autowired
    private IStoreService iStoreService;




    @RequestMapping("loginPro")
    public String loginPro(){
        return "back/login";
    }
    @RequestMapping("login")
    public String login(User user,Model model){
        ServiceResult result = iUserService.login(user);
        if(result.succeed()){
            User user1= JSON.parseObject(JSON.toJSONString(result.getObject("result")),User.class);
            session.setAttribute("user", user1);
            model.addAttribute("msg", "登录成功！");
            model.addAttribute("url", "/pages/back/admin/index");
        } else {
            model.addAttribute("msg", "用户名或密码错误！");
            model.addAttribute("url", "/pages/back/admin/loginPro");
        }
        return "back/index";
    }
    @RequestMapping("index")
    public String index(){

        return "back/index";
    }

    @ResponseBody
    @RequestMapping(value = "adminStatistics",produces = "application/json; charset=utf-8")
    public String adminStatistics(){
        ServiceResult rs=iStatisticsService.statisticsByAdmin();
        if(rs.succeed()){
            return JSON.toJSONString(rs.getObject("result"));
        }
        return null;
    }

    @RequestMapping("myUser")
    public String myUser(Model model){
        ServiceResult result=iUserService.findAllUser();
        if(result.succeed()){
            model.addAttribute("UserList",result.getObject("result"));
        }
        return "back/myUser";
    }

    @ResponseBody
    @RequestMapping(value = "findUser", produces = "application/json; charset=utf-8")
    public String findUser(String key){
        ServiceResult result=iUserService.findUserByKey(key);
        if(result.succeed()){
            return JSON.toJSONString(result.getObject("result"));
        }
        return "false";
    }

    @ResponseBody
    @RequestMapping(value = "lockUser", produces = "application/json; charset=utf-8")
    public String lockUser(String userId){
        ServiceResult result=iUserService.findUserById(userId);
        if(result.succeed()){
           User user=JSON.parseObject(JSON.toJSONString(result.getObject("result")),User.class);
           if(StringUtil.equals(user.getStatus(),"0")){
               user.setStatus("1");
           }else{
               user.setStatus("0");
           }
           ServiceResult rs=iUserService.updateUser(user);
           if(rs.succeed()){
               return "true";
           }
        }
        return "false";
    }

    @ResponseBody
    @RequestMapping(value = "chognzhiUser", produces = "application/json; charset=utf-8")
    public String chognzhiUser(String userId,String balance){
        ServiceResult result=iUserService.findUserById(userId);
        if(result.succeed()){
            User user=JSON.parseObject(JSON.toJSONString(result.getObject("result")),User.class);
            user.setBalance(user.getBalance().add(new BigDecimal(balance)));
            ServiceResult rs=iUserService.updateUser(user);
            if(rs.succeed()){
                return "true";
            }
        }
        return "false";
    }

    @RequestMapping("myStore")
    public String myStore(Model model){
        ServiceResult result=iStoreService.findStoreModel("");
        if(result.succeed()){
            model.addAttribute("StoreList",result.getObject("result"));
        }
        return "back/myStore";
    }

    @ResponseBody
    @RequestMapping(value = "findStore",produces = "application/json; charset=utf-8")
    public String findStore(String key){
        ServiceResult result=iStoreService.findStoreModel(key);
        if(result.succeed()){
            return JSON.toJSONString(result.getObject("result"));
        }
        return "false";
    }

    @ResponseBody
    @RequestMapping("lockStore")
    public String lockStore(String storeId){
        ServiceResult result=iStoreService.lockStore(storeId);
        if(result.succeed()){
            return "true";
        }
        return "false";
    }







    @Override
    public String getSaveFileDiv() {
        return null;
    }
}

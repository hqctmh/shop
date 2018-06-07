package cn.mh.controller;

import cn.mh.api.IStoreService;
import cn.mh.api.IUserService;
import cn.mh.controller.abs.AbstractController;

import cn.mh.entity.Store;
import cn.mh.entity.User;
import cn.mh.util.CheckContentType;
import cn.mh.util.ServiceResult;
import cn.mh.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController extends AbstractController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IStoreService iStoreService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/register")
    public String register(User user, String code, MultipartFile photo, Model model) {
        if (!StringUtil.equals(code.toUpperCase(), session.getAttribute("rand").toString().toUpperCase())) {//判断验证码
            model.addAttribute("msg", "验证码错误!");
            model.addAttribute("url", "regist.jsp");
            return "forward";
        }
        if(!CheckContentType.checkType(photo.getContentType())){
            model.addAttribute("msg", "上传文件必须为图片!");
            model.addAttribute("url", "/regist.jsp");
            return "forward";
        }
        user.setPic(this.createSingleFileName(photo));
        this.saveUploadFile(photo,request,user.getPic());
        ServiceResult result = iUserService.registUser(user);
        if (result.succeed()) {
            model.addAttribute("msg", "注册成功！");
            model.addAttribute("url", "/");
        } else {
            model.addAttribute("msg", result.getMessage());
            model.addAttribute("url", "/regist.jsp");
        }

        return "forward";
    }

    @RequestMapping("/login")
    public String login(User user, Model model) {
        ServiceResult result = iUserService.login(user);
        if (result.succeed()) {
            User user1= JSON.parseObject(JSON.toJSONString(result.getObject("result")),User.class);
            if(StringUtil.equals(user1.getUserType(),"1")){
                ServiceResult rs=iStoreService.findStoreByLimit(user1.getId(),"","");
                if(rs.succeed()){
                    session.setAttribute("store",JSON.parseArray(JSON.toJSONString(rs.getObject("result")),Store.class).get(0));
                }else{
                    model.addAttribute("msg", "门店查询失败！");
                    model.addAttribute("url", "/login.jsp");
                    return "forward";
                }
            }
            session.setAttribute("user", user1);
            model.addAttribute("msg", "登录成功！");
            model.addAttribute("url", "/");
        } else {
            model.addAttribute("msg", "用户名或密码错误！");
            model.addAttribute("url", "/login.jsp");
        }
        return "forward";
    }

    @RequestMapping("/logout")
    public String logout(Model model){
        session.invalidate();
        model.addAttribute("msg", "注销成功！");
        model.addAttribute("url", "/");
        return "forward";
    }


    @Override
    public String getSaveFileDiv() {
        return "/resources/upload/portrait/";
    }
}

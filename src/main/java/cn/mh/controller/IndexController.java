package cn.mh.controller;

import cn.mh.api.IGoodsService;
import cn.mh.api.IItemService;
import cn.mh.api.IStoreService;
import cn.mh.api.IUserService;
import cn.mh.controller.abs.AbstractController;
import cn.mh.entity.Goods;
import cn.mh.entity.Item;
import cn.mh.util.ServiceResult;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class IndexController extends AbstractController{
    @Autowired
    private IItemService iItemService;
    @Autowired
    private IGoodsService iGoodsService;


    @RequestMapping("/")
    public String index(Model model){
        itemList(model, iItemService);
        ServiceResult rs=iGoodsService.findGodsForIndex();
        if(rs.succeed()){
            model.addAttribute("goodsMap",rs.getObject("result"));
            model.addAttribute("active","index");
        }

        return "front/goods/index";
    }

    @Override
    public String getSaveFileDiv() {
        return null;
    }
}

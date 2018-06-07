package cn.mh.controller.abs;


import cn.mh.api.IItemService;
import cn.mh.entity.Item;
import cn.mh.entity.Store;
import cn.mh.entity.User;
import cn.mh.util.ServiceResult;
import cn.mh.util.StringUtil;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

public abstract class AbstractController {
    /**
     * 实现信息的输出操作
     * @param response response
     * @param obj 输出内容
     */
    public void print(HttpServletResponse response, Object obj ){
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得文件名称，如果没有上传，则返回的是“nophoto.png”文件
     *
     * @param file
     *            接收上传文件
     * @return
     */
    public String createSingleFileName(MultipartFile file) { // 创建文件名称
        if (file == null) {
            return "nophoto.png";
        }
        if (file.getSize() <= 0) { // 没有上传文件
            return "nophoto.png";
        }
        return UUID.randomUUID() + "." + this.getFileExt(file.getContentType());
    }

    /**
     * 取得文件的后缀
     *
     * @return
     */
    public String getFileExt(String contentType) {
        Map<String,String> map=new HashMap<>();
        map.put("image/bmp","bmp");
        map.put("image/gif","gif");
        map.put("image/jpg","jpg");
        map.put("image/jpeg","jpeg");
        map.put("image/png","png");
        return map.get(contentType);
    }

    /**
     * 文件的保存处理操作
     *
     * @param file
     *            包含所有的文件信息
     * @param request
     *            取得绝对路径
     * @return
     */
    public boolean saveUploadFile(MultipartFile file,
                                  HttpServletRequest request, String fileName) {
        String filePath = request.getServletContext().getRealPath(
                this.getSaveFileDiv())
                + fileName;
        File directory=new File(request.getServletContext().getRealPath(
                this.getSaveFileDiv()));
        if(!directory.exists()){
            directory.mkdirs();
        }
        System.out.println(filePath);
        if (file.getSize() > 0) {
            OutputStream output = null;
            InputStream input = null;
            try {
                output = new FileOutputStream(filePath);
                input = file.getInputStream();
                byte data[] = new byte[2048];
                int temp = 0;
                while ((temp = input.read(data)) != -1) {
                    output.write(data, 0, temp);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
    /**
     * 取得上传文件的路径
     *
     * @return
     */
    public abstract String getSaveFileDiv();

    /**
     * 取得用户ID
     * @param session
     * @return
     */
    public  String getUserId(HttpSession session){
        return ((User)session.getAttribute("user")).getId();
    }
    public  User getUser(HttpSession session){
        return (User)session.getAttribute("user");
    }
    public  String getStoreId(HttpSession session){
        return ((Store)session.getAttribute("store")).getId();
    }

    public static void itemList(Model model, IItemService iItemService){
        ServiceResult rs=iItemService.findByLimit("","");
        if(rs.succeed()){
            List<Item> list=rs.getList("result",Item.class);
            Map<String,List<Item>> subItemMap=new HashMap<>();
            List<Item> itemList=new ArrayList<>();
            for(Item item:list){
                if(StringUtil.equals("0",item.getType())){
                    itemList.add(item);
                    subItemMap.put(item.getId(),new ArrayList<>());
                }else{
                    subItemMap.get(item.getIid()).add(item);
                }
            }
            model.addAttribute("itemList",itemList);
            model.addAttribute("subItemMap",subItemMap);
        }
    }
}

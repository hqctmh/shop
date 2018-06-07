package cn.mh.util;

import java.util.HashMap;
import java.util.Map;

public class CheckContentType {
    public static boolean checkType(String string){
        Map<String,String> map=new HashMap<>();
        map.put("image/bmp","bmp");
        map.put("image/gif","gif");
        map.put("image/jpg","jpg");
        map.put("image/jpeg","jpeg");
        map.put("image/png","png");
        if(map.get(string)==null){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(CheckContentType.checkType("image/bmp"));
    }
}

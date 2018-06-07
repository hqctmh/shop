package cn.mh.entity.enums;

import cn.mh.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

public enum ReturnStatusEnum {

    APPLY_RETURN("申请退货","0"),
    AGREE_RETURN("同意退货","1"),
    DO_NOT_AGREE_RETURN("拒绝退货","2"),
    RETURN_SEND("买家已发货","3"),
    RETURN_RECEIVE("卖家已收货","4");

    private String name;

    private String value;

    ReturnStatusEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
    
    public static String getName(String value){
        for(ReturnStatusEnum item:ReturnStatusEnum.values()){
            if(StringUtil.equals(item.getValue(),value)){
                return item.name;
            }
        }
        return null;
    }

    public static String getValue(String name){
        for(ReturnStatusEnum item:ReturnStatusEnum.values()){
            if(StringUtil.equals(item.getName(),name)){
                return item.getValue();
            }
        }
        return null;
    }

    public static Map<String,String> getAll(){
        Map<String,String> map=new HashMap<>();
        for(ReturnStatusEnum item:ReturnStatusEnum.values()){
            map.put(item.getValue(),item.getName());
        }
        return map;
    }
}

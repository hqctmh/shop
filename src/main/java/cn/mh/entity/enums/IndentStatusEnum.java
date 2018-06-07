package cn.mh.entity.enums;

import cn.mh.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

public enum IndentStatusEnum {
    NOT_PAY("未付款","0"),
    PAY("已付款","1"),
    SEND("已发货","2"),
    RECEIVE("已收货","3"),
    CANCEL("已取消","4"),
    RETURN("申请退货","5");

    private String name;

    private String value;

    IndentStatusEnum(String name, String value) {
        this.name=name;
        this.value=value;
    }

    public String getName(){return name;}

    public String getValue(){return value;}

    public static String getName(String value){
        for(IndentStatusEnum item:IndentStatusEnum.values()){
            if(StringUtil.equals(item.getValue(),value)){
                return item.name;
            }
        }
        return null;
    }

    public static String getValue(String name){
        for(IndentStatusEnum item:IndentStatusEnum.values()){
            if(StringUtil.equals(item.getName(),name)){
                return item.getValue();
            }
        }
        return null;
    }

    public static Map<String,String> getAll(){
        Map<String,String> map=new HashMap<>();
        for(IndentStatusEnum item:IndentStatusEnum.values()){
            map.put(item.getValue(),item.getName());
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(IndentStatusEnum.CANCEL.getValue());
        System.out.println(IndentStatusEnum.getName("2"));
    }
}

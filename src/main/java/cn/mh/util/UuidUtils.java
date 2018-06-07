package cn.mh.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.UUID;

public class UuidUtils {
    public static String newid() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    public static String newOrderNum(){
        String time=DateUtil.convertToString(new Date(),"yyyyMMddHHmmss");
        String randomString= RandomStringUtils.randomNumeric(6);
        return time+randomString;
    }

    public static void main(String[] args) {
        System.out.println(newOrderNum());
    }

}

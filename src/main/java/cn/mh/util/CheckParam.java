package cn.mh.util;

import org.apache.log4j.Logger;

public class CheckParam {
    private static Logger log = Logger.getLogger(CheckParam.class);

    private static final String EmptyMes = "不能为空！";
    private static final String SplitChar = ",|\\，";

    public static ServiceResult CheckEmptyObj(ServiceResult result, String strJson, Object... objs) {
        String message = CheckEmptyObject(strJson, objs);
        if (StringUtil.isNotEmpty(message)) {
            result.setAttribute("code", 1);
            result.setAttribute("message", message);
            StackTraceElement temp = Thread.currentThread().getStackTrace()[2];
            log.info(temp.getClassName() + "." + temp.getMethodName() + ":非空校验：" + message);
            //System.out.println(temp.getClassName() + "." + temp.getMethodName() + ":非空校验：" + message);
        }

        return result;
    }

    public static ServiceResult CheckNotAllEmpty(ServiceResult result, Object... objs) {
        boolean flag = CheckNotAllEmptyObject(objs);
        if (!flag) {
            result.setAttribute("code", 1);
            result.setAttribute("message", "入参全部为空！");
            StackTraceElement temp = Thread.currentThread().getStackTrace()[2];
            log.info(temp.getClassName() + "." + temp.getMethodName() + ":非空校验：入参全部为空！");
            System.out.println(temp.getClassName() + "." + temp.getMethodName() + ":非空校验：入参全部为空！");
        }

        return result;
    }

    /**
     * Object非空校验
     *
     * @param strJson
     *            字段名称用“，”分隔 eg: userId,userName,.....
     * @param objs
     *            对应strJson的字段值，要一一对应
     * @return 校验成功返回"",否则返回相应错误。
     *
     *         <pre>
     * 详细用法：
     *  CheckEmptyString("userId,userName,mobile",obj,obj,obj);
     *  CheckEmptyString("用户ID,用户名称,手机号",obj,obj,obj);
     * </pre>
     */
    public static String CheckEmptyObject(String strJson, Object... objs) {
        String[] strArr = strJson.split(SplitChar);
        if (strArr.length != objs.length) {
            return "参数对应错误！";
        }
        for (int i = 0; i < strArr.length; i++) {
            if (objs[i] == null) {
                return strArr[i] + EmptyMes;
            }
            if (objs[i].getClass().equals(String.class)) {
                if (StringUtil.isEmpty(objs[i].toString())) {
                    return strArr[i] + EmptyMes;
                }
                continue;
            }
        }
        return "";
    }

    public static boolean CheckNotAllEmptyObject(Object... objs) {
        boolean falg = false;
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] != null) {
                if (objs[i].getClass().equals(String.class)) {
                    if (StringUtil.isNotEmpty(objs[i].toString())) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
        }
        return falg;
    }

    public static void main(String[] args) {
        ServiceResult result = new ServiceResult();
        System.out.println(CheckEmptyObj(result, "userId，userName,storeid", "", "", ""));
    }
}

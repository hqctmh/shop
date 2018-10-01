import cn.mh.api.IUserService;
import cn.mh.entity.User;
import cn.mh.util.Md5Util;
import cn.mh.util.ServiceResult;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class UserTest extends BaseTest{
    @Autowired
    private IUserService iUserService;
    @Test
    public void TestAddUser(){
        logger.info(Md5Util.getMD5("123456"));
    }

}

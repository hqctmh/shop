import cn.mh.api.IUserService;
import cn.mh.entity.User;
import cn.mh.util.ServiceResult;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTest extends BaseTest{
    @Autowired
    private IUserService iUserService;
    @Test
    public void TestAddUser(){
        User user=new User();
        user.setName("321");
        user.setPassword("123");
        user.setPhone("321");
        ServiceResult result=iUserService.addUser(user);
        logger.info("ServiceResult ----->"+ JSON.toJSONString(result));
    }
}

import cn.mh.api.IItemService;
import cn.mh.api.IStoreService;
import cn.mh.entity.Store;
import cn.mh.util.ServiceResult;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StoreTest extends BaseTest{
    @Autowired
    private IStoreService iStoreService;

    @Test
    public void Testlimit(){
        ServiceResult rs=iStoreService.findStoreByLimit("1872aa1edc0a41809856d1a2b8b49760","","");


        if(rs.succeed()){
//            Store store= (Store) JSON.parseObject(JSON.toJSONString(rs.getObject("rseult")),List.class).get(0);
//            logger.info(JSON.toJSONString(store));
        }
    }
}

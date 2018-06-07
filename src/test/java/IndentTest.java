import cn.mh.api.IIndentService;
import cn.mh.entity.StoreIndent;
import cn.mh.persist.dao.StoreIndentDao;
import cn.mh.persist.mapper.StoreIndentMapper;
import cn.mh.util.DateUtil;
import cn.mh.util.ServiceResult;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

public class IndentTest extends BaseTest{
    @Autowired
    private IIndentService iIndentService;
    @Autowired
    private StoreIndentMapper storeIndentMapper;
    @Autowired
    private StoreIndentDao storeIndentDao;

    @Test
    public void addIndentTest(){

        String str="e19f83a06f9e48c79dab074f35c13a69:2,1:1,5d4b167402304e1fa05ae36f35fa0765:4,15:17,28349a0bb3144f4189fa5593b5a97a1a:2," +
                "28b5e72d4daa41118a50fe7f5642a3ff:1,0d25e250afc446ddbcbcba6f359a8063:10";
        String [] jsonArr=str.split(",");
        Map<String,Integer> map=new HashMap<>();
        for(String item:jsonArr){
            String goodsId=item.split(":")[0];
            String account=item.split(":")[1];
            map.put(goodsId,Integer.parseInt(account));
        }
        //ServiceResult result=iIndentService.addIndent(map,"89a0c085ff5942e694dbe874bba7cbc6");
        //logger.info("result -------->"+ JSON.toJSONString(result));
    }
    @Test
    public void findViewTest(){
        ServiceResult rs=iIndentService.findIndentGoodsForPage(1,6,"89a0c085ff5942e694dbe874bba7cbc6","","0,1,2,3");
        logger.info("result -------->"+ JSON.toJSONString(rs));
    }
    @Test
    public void AddReturnNoteTest(){
        ServiceResult rs=null;
        for(int i=0;i<10;i++) {
            rs = iIndentService.addReturnNote("2abe0867dda14ba68e7142ec32c65b21");
        }
        logger.info("result -------->"+ JSON.toJSONString(rs));
    }
    @Test
    public void SumTest(){
        Date time= DateUtil.convertToDate("2018-06-02 15:23:24","yyyy-MM-dd HH:mm:ss");
        BigDecimal price=storeIndentDao.sumPrice(time,"110");
        logger.info("result -------->"+ JSON.toJSONString(price));
    }
}

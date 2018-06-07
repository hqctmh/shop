package cn.mh.service;

import cn.mh.api.IItemService;
import cn.mh.entity.Item;
import cn.mh.persist.dao.ItemDao;
import cn.mh.util.BaseService;
import cn.mh.util.ServiceResult;
import cn.mh.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("ItemService")
public class ItemServiceImpl extends BaseService implements IItemService {
    @Autowired
    private ItemDao itemDao;
    @Override
    public ServiceResult addItem(Item item) {
        ServiceResult result = new ServiceResult();
        try {
            item.setId(UuidUtils.newid());
            itemDao.addItem(item);
            result.setSucceed();
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }



    @Override
    public ServiceResult findById(String id) {
        ServiceResult result = new ServiceResult();
        try {
            Item item=itemDao.findItemById(id);
            if(item!=null){
                result.setAttribute("result",item);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findByLimit(String type,String iid) {
        ServiceResult result = new ServiceResult();
        try {
            List<Item> itemList=itemDao.findItemByLimit(type,iid);
            if(itemList!=null&&itemList.size()>0){
                result.setAttribute("result",itemList);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findByIds(List<String> ids) {
        ServiceResult result = new ServiceResult();
        try {
            List<Item> itemList=itemDao.findByIds(ids);
            if(itemList!=null&&itemList.size()>0){
                result.setAttribute("result",itemList);
                result.setSucceed();
            }
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }

    @Override
    public ServiceResult findItemBySubItem(String subId) {
        ServiceResult result = new ServiceResult();
        try {
            Item subItem= itemDao.findItemById(subId);
            Item item=itemDao.findItemByLimit("0",subItem.getIid()).get(0);
            Map<String,Item> map=new HashMap<>();
            map.put("item",item);
            map.put("subItem",subItem);
            result.setAttribute("result",map);
            result.setSucceed();
        } catch (Exception e) {
            this.rollback();
            result.setException(e);
        }
        return result;
    }
}

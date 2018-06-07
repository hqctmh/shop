package cn.mh.api;

import cn.mh.entity.Item;
import cn.mh.util.ServiceResult;

import java.util.List;

public interface IItemService {
    /**
     * 添加类别
     * @param item
     * @return
     */
    ServiceResult addItem(Item item);


    /**
     * 根据ID查询类别
     * @param id
     * @return
     */
    ServiceResult findById(String id);

    /**
     * 根据条件查询
     * @param type
     * @return
     */
    ServiceResult findByLimit(String type,String iid);

    /**
     * 通过多个ID查询类别
     * @param ids
     * @return
     */
    ServiceResult findByIds(List<String> ids);

    /**
     * 通过子类别查询父类别
     * @param subId
     * @return
     */
    ServiceResult findItemBySubItem(String subId);
}

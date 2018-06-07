package cn.mh.api;

import cn.mh.entity.Store;
import cn.mh.util.ServiceResult;

public interface IStoreService {
    /**
     * 添加商品
     * @param store
     * @return
     */
    ServiceResult addStore(Store store);

    /**
     * 修改商品
     * @param store
     * @return
     */
    ServiceResult updateStore(Store store);

    /**
     * 通过ID查找商品
     * @param id
     * @return
     */
    ServiceResult findStoreById(String id);

    /**
     * 删除商品
     * @param id
     * @return
     */
    ServiceResult deleteStoreById(String id);

    ServiceResult findStoreByLimit(String userId,String storeName,String status);


    ServiceResult findStoreForPage(int pageNum, int pageSize, String userId,String storeName,String status);

    ServiceResult findStoreModel(String storeName);

    ServiceResult lockStore(String storeId);


}

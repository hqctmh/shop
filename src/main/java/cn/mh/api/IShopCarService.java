package cn.mh.api;

import cn.mh.entity.ShopCar;
import cn.mh.util.ServiceResult;

public interface IShopCarService {
    /**
     * 添加购物车
     *
     * @return
     */
    ServiceResult addShopCar(ShopCar shopCar);

    /**
     * 修改购物车
     * @param shopCar
     * @return
     */
    ServiceResult updateShopCar(ShopCar shopCar);

    /**
     * 通过ID查找购物车
     * @param id
     * @return
     */
    ServiceResult findShopCarById(String id);

    /**
     * 删除购物车
     * @param id
     * @return
     */
    ServiceResult deleteShopCarById(String id);

    /**
     * 根据条件查询
     * @param userId
     * @param storeId
     * @return
     */
    ServiceResult findShopCarByLimit(String userId, String storeId,String goodsId);

    /**
     * 查询出用户的购物车
     * @param userId
     * @return
     */
    ServiceResult findShopCarByUserId(String userId);


}

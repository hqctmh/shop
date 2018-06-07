package cn.mh.api;

import cn.mh.util.ServiceResult;

import java.util.List;
import java.util.Map;

public interface IIndentService {
    /**
     * 添加订单
     * @param jsonMap 含有商品id以及数量的Map
     * @return
     */
    ServiceResult addIndent(Map<String,Integer>jsonMap,String userId,String addressId);

    /**
     * 订单付款
     * @param storeIndentIdList
     * @return
     */
    ServiceResult payIndent(List<String> storeIndentIdList,String userId);

    /**
     * 根据购物车传递的参数查询商品
     * @param jsonMap
     * @return
     */
    ServiceResult findGoodsForIndent(Map<String,Integer>jsonMap);

    /**
     * 查询订单
     * @param pageNum
     * @param pageSize
     * @param userId
     * @param storeId
     * @param status
     * @return
     */
    ServiceResult findIndentGoodsForPage(int pageNum,int pageSize,String userId, String storeId,String status);

    /**
     * 修改订单状态
     * @param storeIndentId
     * @param status
     * @return
     */
    ServiceResult updateStoreIndent(String storeIndentId,String status);

    /**
     * 查询店铺订单
     * @param storeIndentId
     * @return
     */
    ServiceResult findStoreIndentById(String storeIndentId);

    /**
     * 添加退货信息
     * @param storeIndentId
     * @return
     */
    ServiceResult addReturnNote(String storeIndentId);

    /**
     * 分页查询退货视图
     * @param pageNum
     * @param pageSize
     * @param storeIndentId
     * @param storeId
     * @param userId
     * @param status
     * @return
     */
    ServiceResult findReturnDetailForPage(int pageNum,int pageSize,String storeIndentId,String storeId,String userId,String status);

    /**
     * 修改退货单状态
     * @param mainId
     * @param status
     * @return
     */
    ServiceResult updateReturnMain(String mainId,String status);

    /**
     * 退款加库存
     * @param mainId
     * @return
     */
    ServiceResult returnGoods(String mainId);

    /**
     *
     * @param storeId
     * @return
     */
    ServiceResult statisticsSell(String storeId);

    ServiceResult statisticsSellGoods(String storeId);

    /**
     * 用户留言
     * @param goodsId
     * @param userId
     * @return
     */
    ServiceResult message(String goodsId,String userId,String msg);

    /**
     * 123
     * @param pageNum
     * @param pageSize
     * @param storeId
     * @return
     */
    ServiceResult myMessage(int pageNum,int pageSize,String storeId);
}

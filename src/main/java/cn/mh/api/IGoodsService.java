package cn.mh.api;

import cn.mh.entity.Goods;
import cn.mh.util.ServiceResult;

public interface IGoodsService {
    /**
     * 添加商品
     * @param goods
     * @return
     */
    ServiceResult addGoods(Goods goods);

    /**
     * 修改商品
     * @param goods
     * @return
     */
    ServiceResult updateGoods(Goods goods);

    /**
     * 通过ID查找商品
     * @param id
     * @return
     */
    ServiceResult findGoodsById(String id);

    /**
     * 删除商品
     * @param id
     * @return
     */
    ServiceResult deleteGoodsById(String id);

    /**
     * 条件查询
     * @param iid
     * @param subId
     * @param storeId
     * @param name
     * @param delFlag
     * @return
     */
    ServiceResult findGoodsByLimit(String iid,String subId,String storeId,String name,String delFlag);

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param iid
     * @param subId
     * @param storeId
     * @param name
     * @param delFlag
     * @return
     */
    ServiceResult findGoodsForPage(int pageNum,int pageSize,String iid,String subId,String storeId,String name,String delFlag);

    /**
     * 查询首页商品
     * @return
     */
    ServiceResult findGodsForIndex();
}

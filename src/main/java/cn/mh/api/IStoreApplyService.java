package cn.mh.api;

import cn.mh.util.ServiceResult;

public interface IStoreApplyService {
    /**
     * 添加店铺申请信息
     * @param userId
     * @param storeName
     * @return
     */
    ServiceResult addStoreApply(String userId, String storeName);

    /**
     * 同意申请
     * @param applyId
     * @return
     */
    ServiceResult agreeApply(String applyId);

    /**
     * 拒绝申请
     * @param applyId
     * @return
     */
    ServiceResult disAgreeApply(String applyId);

    /**
     * 查询申请信息
     * @param userId
     * @return
     */
    ServiceResult findApply(String userId,String status);

    /**
     *
     * @param userId
     * @param status
     * @return
     */
    ServiceResult findApplyByAdmin();
}

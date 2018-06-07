package cn.mh.api;

import cn.mh.entity.User;
import cn.mh.util.ServiceResult;

public interface IUserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    ServiceResult addUser(User user);

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    ServiceResult updateUser(User user);

    /**
     * 通过ID查询用户
     * @param id
     * @return
     */
    ServiceResult findUserById(String id);

    /**
     * 删除用户
     * @param id
     * @return
     */
    ServiceResult deleteUserById(String id);

    /**
     * 注册用户
     * @param user
     * @return
     */
    ServiceResult registUser(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    ServiceResult login(User user);

    /**
     * 支付密码
     * @param userId
     * @param psd
     * @return
     */
    ServiceResult payPass(String userId,String psd);

    /**
     * 查询所有买家用户
     * @return
     */
    ServiceResult findAllUser();

    /**
     * 通过关键字查询用户
     * @param keyWord
     * @return
     */
    ServiceResult findUserByKey(String keyWord);
}

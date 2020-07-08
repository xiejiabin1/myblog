package com.xie.myblog.service;

import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    /**
     * 模糊查询所有用户
     * @param typeId
     * @param name
     * @return
     */
    BtoResult<PageInfo<User>> selectUserAll(Integer pageNum, Integer pageSize, Long typeId, String name);

    /**
     * 查询所有管理员
     * @return
     */
    BtoResult<List<User>> selectAdminAll();

    /**
     * 根据userId查询用户信息
     * @param userId
     * @return
     */
    BtoResult<User> selectUserByUserId(Long userId);

    /***
     * 选择性修改用户信息
     * @param user
     * @return
     */
    BtoResult<Boolean> updateUserSelect(User user);

    /**
     * 根据userId修改用户状态
     * @param userId
     * @return
     */
    boolean delUser(Long userId,Integer delStatus);

    /**
     * 新增用户
     * @param user
     * @return
     */
    BtoResult<Boolean> addUser(User user);

    /**
     * 普通用户登录
     * @param userName
     * @param password
     * @return
     */
    BtoResult<User> login(String userName, String password);

    /**
     * 管理员登录
     * @param userName
     * @param password
     * @return
     */
    BtoResult<User> adminLogin(String userName, String password);

    /**
     * 查询用户的数量
     * @return
     */
    BtoResult<Integer> selectUserCount(@Param("typeId") Long typeId);

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    boolean isUserName(String userName);
}

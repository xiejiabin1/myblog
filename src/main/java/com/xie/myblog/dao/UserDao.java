package com.xie.myblog.dao;

import com.xie.myblog.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    /**
     * 条件查询所有用户
     * @param map
     * @return
     */
    List<User> selectUserAll(Map<String,Object> map);

    /**
     * 查询所有管理员
     * @return
     */
    List<User> selectAdminAll();

    /**
     * 根据userId查询用户信息
     * @param userId
     * @return
     */
    User selectUserByUserId(@Param("userId") Long userId);

    /***
     * 选择性修改用户信息
     * @param user
     * @return
     */
    int updateUserSelect(User user);

    /**
     * 根据userId修改用户状态
     * @param userId
     * @return
     */
    int delUser(@Param("userId") Long userId,@Param("delStatus") Integer delStatus);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 普通用户登录
     * @param userName
     * @param password
     * @return
     */
    User login(@Param("userName") String userName, @Param("password") String password);

    /**
     * 管理员登录
     * @param userName
     * @param password
     * @return
     */
    User adminLogin(@Param("userName") String userName, @Param("password") String password);

    /**
     * 查询用户的数量
     * @return
     */
    int selectUserCount(@Param("typeId") Long typeId);

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    int isUserName(@Param("userName") String userName);

    /**
     * 查询用户昵称和邮箱
     * @param userId
     * @return
     */
    User getUserById(@Param("userId") Long userId);
}

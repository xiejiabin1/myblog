package com.xie.myblog.dao;

import com.xie.myblog.po.UserType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserTypeDao {

    /**
     * 根据name模糊查询角色分类
     * @param name
     * @return
     */
    List<UserType> selectUserTypeAll(@Param("name") String name);

     /**
     * 根据id查询角色分类信息
     * @param id
     * @return
     */
    UserType selectUserTypeById(@Param("id") Long id);

    /**
     * 新增角色分类
     * @param userType
     * @return
     */
    int addUserType(UserType userType);

    /**
     * 根据id删除角色分类
     * @param id
     * @return
     */
    int delUserType(@Param("id") Long id);

    /**
     * 修改角色分类
     * @param userType
     * @return
     */
    int updateUserType(UserType userType);
}

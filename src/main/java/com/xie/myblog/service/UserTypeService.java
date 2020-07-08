package com.xie.myblog.service;

import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.UserType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTypeService {
    /**
     * 根据name模糊查询角色分类
     * @param name
     * @return
     */
    BtoResult<PageInfo<UserType>> selectUserTypeAll(Integer pageNum, Integer pageSize, String name);

    /**
     * 查询所有角色分类
     * @return
     */
    BtoResult<List<UserType>> selectUserTypeList();

    /**
     * 根据id查询角色分类信息
     * @param id
     * @return
     */
    BtoResult<UserType> selectUserTypeById(Long id);

    /**
     * 新增角色分类
     * @param userType
     * @return
     */
    BtoResult<Boolean> addUserType(UserType userType);

    /**
     * 根据id删除角色分类
     * @param id
     * @return
     */
    BtoResult<Boolean> delUserType(@Param("id") Long id);

    /**
     * 修改角色分类
     * @param userType
     * @return
     */
    BtoResult<Boolean> updateUserType(UserType userType);
}

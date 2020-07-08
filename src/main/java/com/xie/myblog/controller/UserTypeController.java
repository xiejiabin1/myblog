package com.xie.myblog.controller;

import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.UserType;
import com.xie.myblog.service.UserTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/26 22:58
 */
@RestController
@RequestMapping("userType")
public class UserTypeController {
    @Resource
    private UserTypeService userTypeService;

    /**
     * 根据name模糊查询角色分类
     * @param name
     * @return
     */
    @GetMapping("selectUserTypeAll")
    public BtoResult<PageInfo<UserType>> selectUserTypeAll(Integer pageNum, Integer pageSize, String name) {
        return userTypeService.selectUserTypeAll(pageNum,pageSize,name);
    }

    /**
     * 根据name模糊查询角色分类
     * @return
     */
    @GetMapping("selectUserTypeList")
    public BtoResult<List<UserType>> selectUserTypeList() {
        return userTypeService.selectUserTypeList();
    }

    /**
     * 根据id查询角色分类信息
     * @param id
     * @return
     */
    @GetMapping("selectUserTypeById")
    public BtoResult<UserType> selectUserTypeById(Long id) {
        return userTypeService.selectUserTypeById(id);
    }

    /**
     * 新增角色分类
     * @param userType
     * @return
     */
    @PostMapping("addUserType")
    public BtoResult<Boolean> addUserType(UserType userType) {
        return userTypeService.addUserType(userType);
    }

    /**
     * 根据id删除角色分类
     * @param id
     * @return
     */
    @PostMapping("delUserType")
    public BtoResult<Boolean> delUserType(Long id) {
        return userTypeService.delUserType(id);
    }

    /**
     * 修改角色分类
     * @param userType
     * @return
     */
    @PostMapping("updateUserType")
    public BtoResult<Boolean> updateUserType(UserType userType) {
        return userTypeService.updateUserType(userType);
    }
}

package com.xie.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.dao.UserTypeDao;
import com.xie.myblog.po.User;
import com.xie.myblog.po.UserType;
import com.xie.myblog.service.UserTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/26 19:28
 */
@Service
public class UserTypeServiceImpl implements UserTypeService {
    @Resource
    private UserTypeDao userTypeDao;

    @Override
    public BtoResult<PageInfo<UserType>> selectUserTypeAll(Integer pageNum, Integer pageSize, String name) {
        if(pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<UserType> list = userTypeDao.selectUserTypeAll(name);
        PageInfo<UserType> pageInfo = new PageInfo<>(list);
        return new BtoResult<>(true,"查询成功",pageInfo);
    }

    @Override
    public BtoResult<List<UserType>> selectUserTypeList() {
        return new BtoResult<>(true,"查询成功",userTypeDao.selectUserTypeAll(""));
    }

    @Override
    public BtoResult<UserType> selectUserTypeById(Long id) {
        return new BtoResult<>(true,"查询成功",userTypeDao.selectUserTypeById(id));
    }

    @Transactional
    @Override
    public BtoResult<Boolean> addUserType(UserType userType) {
        return new BtoResult<>(true,"新增成功",userTypeDao.addUserType(userType)>0 ? true : false);
    }

    @Transactional
    @Override
    public BtoResult<Boolean> delUserType(Long id) {
        return new BtoResult<>(true,"删除成功",userTypeDao.delUserType(id)>0 ? true : false);
    }

    @Transactional
    @Override
    public BtoResult<Boolean> updateUserType(UserType userType) {
        return new BtoResult<>(true,"修改成功",userTypeDao.updateUserType(userType)>0 ? true : false);
    }
}

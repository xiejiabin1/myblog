package com.xie.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.dao.UserDao;
import com.xie.myblog.po.User;
import com.xie.myblog.service.UserService;
import com.xie.myblog.util.CookieUtil;
import com.xie.myblog.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/26 19:22
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public BtoResult<PageInfo<User>> selectUserAll(Integer pageNum, Integer pageSize, Long typeId, String name) {
        Map<String,Object> map = new HashMap<>();
        if(pageNum == null){
            pageNum = 1;
        }
        if(typeId != null){
            map.put("typeId",typeId);
        }
        if(name != null){
            map.put("name",name);
        }

        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userDao.selectUserAll(map);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return new BtoResult<>(true,"查询成功",pageInfo);
    }

    @Override
    public BtoResult<List<User>> selectAdminAll() {
        return new BtoResult<>(true,"查询成功",userDao.selectAdminAll());
    }

    @Override
    public BtoResult<User> selectUserByUserId(Long userId) {
        return new BtoResult<>(true,"查询成功",userDao.selectUserByUserId(userId));
    }

    @Transactional
    @Override
    public BtoResult<Boolean> updateUserSelect(User user) {
        return new BtoResult<>(true,"修改成功",userDao.updateUserSelect(user)>0 ? true : false);
    }

    @Transactional
    @Override
    public boolean delUser(Long userId,Integer delStatus) {
        return userDao.delUser(userId ,delStatus)>0 ? true : false;
    }

    @Transactional
    @Override
    public BtoResult<Boolean> addUser(User user) {
        if(user.getNickName() != null){
            user.setNickName(user.getUserName());
        }
        return new BtoResult<>(true,"修改成功",userDao.addUser(user)>0 ? true : false);
    }

    @Transactional
    @Override
    public BtoResult<User> login(String userName, String password) {
        User user = userDao.login(userName, MD5Util.encode(password));
        if(user != null){
            int delStatus = user.getDelStatus();
            if(delStatus == 0){
                return new BtoResult<>(true,"登录成功！",user);
            } else if (delStatus == 1) {
                return new BtoResult<>(false,"登录失败，账号被冻结！");
            } else if(delStatus == 2){
                return new BtoResult<>(false,"登录失败，账号被删除！");
            }
        }
        return new BtoResult<>(false,"用户名或者密码错误");
    }

    @Transactional
    @Override
    public BtoResult<User> adminLogin(String userName, String password) {
        User user = userDao.adminLogin(userName, MD5Util.encode(password));
        if(user != null){
            int delStatus = user.getDelStatus();
            if(delStatus == 0){
                return new BtoResult<>(true,"登录成功！",user);
            } else if (delStatus == 1) {
                return new BtoResult<>(false,"登录失败，账号被冻结！");
            } else if(delStatus == 2){
                return new BtoResult<>(false,"登录失败，账号被删除！");
            }
        }
        return new BtoResult<>(false,"用户名或者密码错误");
    }

    @Override
    public BtoResult<Integer> selectUserCount(Long typeId) {
        return new BtoResult<>(true,"查询成功",userDao.selectUserCount(typeId));
    }

    @Override
    public boolean isUserName(String userName) {
        return userDao.isUserName(userName)>0 ? true : false;
    }
}

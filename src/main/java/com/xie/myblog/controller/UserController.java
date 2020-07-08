package com.xie.myblog.controller;

import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.User;
import com.xie.myblog.service.UserService;
import com.xie.myblog.util.CookieUtil;
import com.xie.myblog.util.FtpUtil;
import com.xie.myblog.util.MD5Util;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @description: 用户控制层
 * @author: 谢
 * @time: 2020/6/26 19:40
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 模糊查询所有用户
     * @param typeId
     * @param name
     * @return
     */
    @GetMapping("selectUserAll")
    public BtoResult<PageInfo<User>> selectUserAll(Integer pageNum, Integer pageSize,
                                                  Long typeId, String name){
        return userService.selectUserAll(pageNum,pageSize,typeId,name);
    }

    /**
     * 查询所有管理员
     * @return
     */
    @GetMapping("selectAdminAll")
    public BtoResult<List<User>> selectAdminAll() {
        return userService.selectAdminAll();
    }

    /**
     * 根据userId查询用户信息
     * @param userId
     * @return
     */
    @GetMapping("selectUserByUserId")
    public BtoResult<User> selectUserByUserId(Long userId){
        return userService.selectUserByUserId(userId);
    }

    /***
     * 选择性修改用户信息
     * @param user
     * @return
     */
    @PostMapping("updateUserSelect")
    public BtoResult<Boolean> updateUserSelect(@RequestParam(value = "file", required = false) MultipartFile file, User user){
        if (file != null && !file.isEmpty()) {
            String olderName = file.getOriginalFilename();
            String suffix = FilenameUtils.getExtension(olderName);
            if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png")) {
                String newName = UUID.randomUUID().toString().replace("-", "").substring(15).toLowerCase() + "." + suffix;
                FtpUtil.FtpConfig ftpConfig = new FtpUtil.FtpConfig();
                ftpConfig.setIp("152.136.107.185");
                ftpConfig.setPort(21);
                ftpConfig.setUserName("ftpuser");
                ftpConfig.setPassword("Xie123");
                try {
                    //上传图片
                    FtpUtil.upload(ftpConfig, "/myblog/", file.getInputStream(), newName);
                    String fileName = user.getAvatar();
                    if (fileName != null) {
                        String str = "default.jpg";
                        if (!str.equals(fileName)) {
                            //删除原图片
                            FtpUtil.deleteFile(ftpConfig, "/myblog/", fileName);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                user.setAvatar(newName);
            } else {
                return new BtoResult<>(false,"图片格式不对");
            }
        }

        if(user.getPassword() != null){
            user.setPassword(MD5Util.encode(user.getPassword()));
        }
        user.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        return userService.updateUserSelect(user);
    }

    /**
     * 根据userId修改用户状态
     * @param userId
     * @return
     */
    @PostMapping("delUser")
    boolean delUser(Long userId,Integer delStatus){
        return userService.delUser(userId,delStatus);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("addUser")
    BtoResult<Boolean> addUser(MultipartFile file, User user){
        if (file != null && !file.isEmpty()) {
            String olderName = file.getOriginalFilename();
            String suffix = FilenameUtils.getExtension(olderName);
            if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png")) {
                String newName = UUID.randomUUID().toString().replace("-", "").substring(15).toLowerCase() + "." + suffix;
                FtpUtil.FtpConfig ftpConfig = new FtpUtil.FtpConfig();
                ftpConfig.setIp("152.136.107.185");
                ftpConfig.setPort(21);
                ftpConfig.setUserName("ftpuser");
                ftpConfig.setPassword("Xie123");
                try {
                    //上传图片
                    FtpUtil.upload(ftpConfig, "/myblog/", file.getInputStream(), newName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                user.setAvatar(newName);
            } else {
                return new BtoResult<>(false,"图片格式不对");
            }
        }else {
            user.setAvatar("default.jpg");
        }

        if(user.getPassword() != null){
            user.setPassword(MD5Util.encode(user.getPassword()));
        }
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return userService.addUser(user);
    }

    /**
     * 普通用户登录
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("userLogin")
    public BtoResult<User> login( String userName, String password) {
        BtoResult<User> btoResult = userService.login(userName,password);
        if( btoResult.getData() != null){
            User user = btoResult.getData();
//            if(user.getTypeId() != 3){
//                CookieUtil.setCookie(request,response,"admin","2501267970");
//            }else {
//                CookieUtil.setCookie(request,response,"user","1499618025");
//            }
//            CookieUtil.setCookie(request,response,"userId",Long.toString(user.getUserId()));
//            CookieUtil.setCookie(request,response,"typeId",Long.toString(user.getTypeId()));
            return new BtoResult<>(true,btoResult.getMsg(),user);
        }
        return new BtoResult<>(false,btoResult.getMsg());
    }

    /**
     * 管理员登录
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("adminLogin")
    public BtoResult<User> adminLogin( String userName, String password) {
        BtoResult<User> btoResult = userService.adminLogin(userName,password);
        if( btoResult.getData() != null){
            User user = btoResult.getData();
//            CookieUtil.setCookie(request,response,"admin","2501267970");
//            CookieUtil.setCookie(request,response,"userId",Long.toString(user.getUserId()));
//            CookieUtil.setCookie(request,response,"typeId",Long.toString(user.getTypeId()));
//            CookieUtil.setCookie(request,response,"user","1499618025");
            return new BtoResult<>(true,btoResult.getMsg(),user);
        }
        return new BtoResult<>(false,btoResult.getMsg());
    }

    /**
     * 查询用户的数量
     * @return
     */
    @GetMapping("selectUserCount")
    public BtoResult<Integer> selectUserCount(@RequestParam(value = "typeId", required = false) Long typeId){
        return userService.selectUserCount(typeId);
    }

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    @GetMapping("isUserName")
    public boolean isUserName(String userName){
        return userService.isUserName(userName);
    }

    @GetMapping("sess")
    public void sess(HttpServletRequest request){
        HttpSession session = request.getSession(true);
    }

    /**
     * 注销
     * @param request
     * @param response
     * @param userId
     * @param typeId
     * @param admin
     */
    @GetMapping("exit")
    public void exit(HttpServletRequest request, HttpServletResponse response,
                     String userId,String typeId,String admin,String user){
        if(userId != null && !userId.equals("")){
            CookieUtil.deleteCookie(request,response,"userId");
        }
        if(typeId != null && !typeId.equals("")){
            CookieUtil.deleteCookie(request,response,"typeId");
        }
        if(admin != null && !admin.equals("")){
            CookieUtil.deleteCookie(request,response,"admin");
        }
        if(user != null && !user.equals("")){
            CookieUtil.deleteCookie(request,response,"user");
        }
    }
}

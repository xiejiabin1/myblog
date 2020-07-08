package com.xie.myblog.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description: 用户实体类
 * @author: 谢
 * @time: 2020/6/26 12:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {
    private Long userId;              //用户id
    private String nickName;          //用户昵称
    private String userName;          //用户名
    private String password;          //用户密码
    private String email;             //邮箱
    private Long typeId;              //角色id
    private String avatar;            //头像地址
    private Timestamp createTime;     //创建时间
    private Timestamp updateTime;     //更新时间
    private Integer delStatus;        //状态

    private UserType userType;
}

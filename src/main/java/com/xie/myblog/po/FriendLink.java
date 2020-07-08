package com.xie.myblog.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description: 友链实体类
 * @author: 谢
 * @time: 2020/6/26 12:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FriendLink implements Serializable {
    private Long friendLinkId;          //友链id
    private String blogName;            //博客网站名称
    private String blogAddress;         //博客网址
    private String blogPicture;         //博客图片地址
    private String description;         //博客描述
    private Timestamp createTime;       //创建时间
}

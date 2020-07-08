package com.xie.myblog.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 博客实体类
 * @author: 谢
 * @time: 2020/6/26 12:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Blog implements Serializable {
    private Long blogId;                //博客id
    private String title;               //标题
    private String content;             //内容
    private String firstPicture;        //首图地址
    private String flag;                //标记
    private Integer views;              //浏览次数
    private Integer commentCount;       //评论次数
    private Boolean appreciation;       //是否开启赞赏
    private Boolean shareStatement;     //是否开启版权声明
    private Boolean commentBled;        //是否开启评论
    private Boolean published;          //是否发布
    private Boolean recommend;          //是否推荐
    private Timestamp createTime;       //创建时间
    private Timestamp updateTime;       //更新时间
    private String description;         //博客描述
    private Long typeId;                //分类id
    private Long userId;                //用户id

    private BlogType blogType;
    private User user;
    private List<Comment> commentList = new ArrayList<>();
}

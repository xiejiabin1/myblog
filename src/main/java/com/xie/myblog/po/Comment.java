package com.xie.myblog.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 评论实体类
 * @author: 谢
 * @time: 2020/6/26 12:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Comment implements Serializable {
    private Long commentId;             //评论id
    private Long blogId;                //博客id
    private Long userId;                //用户id
    private Long parentCommentId;       //父评论id
    private String content;             //评论内容
    private Boolean adminComment;       //是否为管理员评论
    private Timestamp createTime;       //创建时间

    private User user;
    private List<Comment> replyCommentList = new ArrayList<>(); //回复评论
    private Comment parentComment;      //父评论
}

package com.xie.myblog.service;

import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {

    /**
     * 查询该博客的评论列表
     * @param blogId
     * @return
     */
    List<Comment> listComment(Long blogId);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    boolean addComment(Comment comment,Long typeId);

    /**
     * 根据评论id查询评论信息
     * @param commentId
     * @return
     */
    Comment selectCommentById(Long commentId);

    /**
     * 查询评论次数
     * @return
     */
    BtoResult<Integer> selectCount();
}

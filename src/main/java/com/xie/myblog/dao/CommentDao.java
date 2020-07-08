package com.xie.myblog.dao;

import com.xie.myblog.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface CommentDao {

    /**
     * 添加评论
     * @param comment
     * @return
     */
    int addComment(Comment comment);

    /**
     * 查询父级评论
     * @param parentCommentId
     * @param blogId
     * @return
     */
    List<Comment> selectCommentByParent(@Param("parentCommentId") Long parentCommentId, @Param("blogId") Long blogId);

    /**
     * 查询该评论的下一级回复
     * @param commentId
     * @param blogId
     * @return
     */
    List<Comment> selectReplayOne(@Param("commentId") Long commentId, @Param("blogId") Long blogId);

    /**
     * 查询该评论的二级回复以及所有子集回复
     * @param childCommentId
     * @param blogId
     * @return
     */
    List<Comment> selectReplayTwoList(@Param("childCommentId") Long childCommentId, @Param("blogId") Long blogId);

    /**
     * 根据评论id查询评论信息
     * @param commentId
     * @return
     */
    Comment selectCommentById(@Param("commentId") Long commentId);

    /**
     * 查询评论次数
     * @return
     */
    Integer selectCount();
}

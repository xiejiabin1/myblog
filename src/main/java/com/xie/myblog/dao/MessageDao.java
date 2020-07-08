package com.xie.myblog.dao;

import com.xie.myblog.po.Comment;
import com.xie.myblog.po.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDao {
    /**
     * 添加留言
     * @param message
     * @return
     */
    int addMessage(Message message);

    /**
     * 查询父级留言
     * @param parentMessageId
     * @return
     */
    List<Message> selectMessageByParent(@Param("parentMessageId") Long parentMessageId);

    /**
     * 查询该留言的下一级回复
     * @param messageId
     * @return
     */
    List<Message> selectReplayOne(@Param("messageId") Long messageId);

    /**
     * 查询该评论的二级回复以及所有子集回复
     * @param childMessageId
     * @return
     */
    List<Message> selectReplayTwoList(@Param("childMessageId") Long childMessageId);

    /**
     * 根据评论id查询评论信息
     * @param messageId
     * @return
     */
    Message selectMessageById(@Param("messageId") Long messageId);

    /**
     * 查询评论次数
     * @return
     */
    Integer selectCount();
}

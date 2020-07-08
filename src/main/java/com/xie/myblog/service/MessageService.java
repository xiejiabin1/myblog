package com.xie.myblog.service;

import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.Comment;
import com.xie.myblog.po.Count;
import com.xie.myblog.po.Message;

import java.util.List;

/**
 * @description:
 * @author: 谢
 * @time: 2020/7/3 10:13
 */
public interface MessageService {
    /**
     * 查询该博客的评论列表
     * @return
     */
    BtoResult<List<Message>> listMessage();

    /**
     * 添加评论
     * @param message
     * @return
     */
    boolean addMessage(Message message,Long typeId);

    /**
     * 根据评论id查询评论信息
     * @param messageId
     * @return
     */
    Message selectMessageById(Long messageId);

    /**
     * 查询评论次数
     * @return
     */
    BtoResult<Integer> selectCount();
}

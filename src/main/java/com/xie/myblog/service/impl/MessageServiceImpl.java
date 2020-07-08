package com.xie.myblog.service.impl;

import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.dao.MessageDao;
import com.xie.myblog.po.Comment;
import com.xie.myblog.po.Message;
import com.xie.myblog.service.MessageService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 谢
 * @time: 2020/7/5 7:55
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;

    //存放迭代找出的所有子代的集合
    private List<Message> tempReplayList = new ArrayList<>();

    @Transactional
    @Override
    public BtoResult<List<Message>> listMessage() {
        //查询所有评论（父评论id为0）
        List<Message> messageList = messageDao.selectMessageByParent(Long.parseLong("0"));
        for(Message message : messageList){
            Long messageId = message.getMessageId();
            List<Message> childMessageList  = messageDao.selectReplayOne(messageId);
            //查询子评论
            selectChildMessage(childMessageList);
            message.setReplyMessagesList(tempReplayList);
            tempReplayList = new ArrayList<>();
        }
        return new BtoResult<>(true,"查询成功",messageList);
    }

    /**
     * 查询出子留言
     * @param childMessageList
     */
    private void selectChildMessage(List<Message> childMessageList){
        //判断该评论是否有一级子回复
        if(childMessageList .size() > 0){
            //循环找出子评论的id
            for(Message childMessage : childMessageList ){
                childMessage.setParentMessage(selectMessageById(childMessage.getParentMessageId()));
                tempReplayList.add(childMessage);
                Long childMessageId = childMessage.getMessageId();
                //查询二级以及所有子集回复
                selectGrandchildMessage(childMessageId);
            }
        }
    }

    /**
     * 循环迭代找出子集回复
     * @param childMessageId
     */
    private void selectGrandchildMessage(Long childMessageId){
        //根据子一级评论的id找到子二级评论
        List<Message> replayMessageList = messageDao.selectReplayTwoList(childMessageId);

        if(replayMessageList.size() > 0){
            for(Message replayMessage : replayMessageList){
                Long replayMessageId = replayMessage.getMessageId();
                replayMessage.setParentMessage(selectMessageById(replayMessage.getParentMessageId()));
                tempReplayList.add(replayMessage);
                //循环迭代找出子集回复
                selectGrandchildMessage(replayMessageId);
            }
        }
    }

    @Override
    public boolean addMessage(Message message,Long typeId) {
        message.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if(typeId == 1){
            message.setAdminMessage(true);
        }else {
            message.setAdminMessage(false);
        }
        return messageDao.addMessage(message)>0 ? true : false;
    }

    @Override
    public Message selectMessageById(Long messageId) {
        return messageDao.selectMessageById(messageId);
    }

    @Override
    public BtoResult<Integer> selectCount() {
        return new BtoResult<>(true,"查询成功",messageDao.selectCount());
    }
}

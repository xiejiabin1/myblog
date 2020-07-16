package com.xie.myblog.service.impl;

import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.dao.MessageDao;
import com.xie.myblog.dao.UserDao;
import com.xie.myblog.po.Comment;
import com.xie.myblog.po.Message;
import com.xie.myblog.po.User;
import com.xie.myblog.service.MessageService;
import com.xie.myblog.util.MailSendUtil;
import com.xie.myblog.vo.MailInfo;
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
    @Resource
    private UserDao userDao;

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
            Message parentMessage = messageDao.selectMessageById(message.getParentMessageId());
            User user = userDao.getUserById(parentMessage.getUserId());
            if(user != null){
                MailInfo info = new MailInfo();
                info.setHost("smtp.163.com");
                info.setFormName("xie430423@163.com");
                info.setFormPassword("VOQGWBZYNBOAVZGE");
                info.setReplayAddress("xie430423@163.com");
                info.setToAddress(user.getEmail());
                info.setSubject("哇！！！先谢郭嘉 给您回复啦~~~");
                String st = "<div><div style=\"background: white;width: 95%;max-width: 800px;margin: auto auto;border-radius: 5px;border:orange 1px solid;overflow: hidden;-webkit-box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.12);box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.18);\">\n" +
                        "            <header style=\"overflow: hidden;\"><img style=\"width:100%;z-index: 666;\" src=\"https://cdn.jsdelivr.net/gh/xiejiabin1/images/2.jpg\" /></header>\n" +
                        "            <div style=\"padding: 5px 20px;\">\n" +
                        "                <p style=\"position: relative; color: white;float: left; z-index: 999; background: orange;padding: 5px 30px;margin: -25px auto 0 ;box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.30)\">\n" +
                        "                Dear" +user.getNickName()+
                        "                </p><br/>"+
                        "                <center>\n" +
                        "                    <h3>\n" +
                        "                    来自<span style=\"text-decoration: none;color: orange \">先谢郭嘉</span>的回复\n" +
                        "                    </h3>\n" +
                        "                </center><br/>"+
                        "                &nbsp; &nbsp;<p>您在<a style=\"text-decoration: none;color: orange \" target=\"_blank\" href=\"https://xiejiabin.online\" rel=\"noopener\">&nbsp;先谢郭嘉的博客 </a>上曾留言：</p>&nbsp; &nbsp;\n" +
                        "                <center>\n" +
                        "                <p>" + parentMessage.getContent() + "</p>\n" +
                        "                </center>"+
                        "                &nbsp; &nbsp;<p style=\"padding-bottom: 10px;\">\n" +
                        "                    <span style=\"color: orange;\">先谢郭嘉</span> 给您回复啦~~~：\n" +
                        "                </p>&nbsp; &nbsp;\n" +
                        "                <center style=\"border-bottom:#ddd 1px solid;border-left:#ddd 1px solid;padding-bottom:20px;background-color:#eee;margin:15px 0px;padding-left:20px;padding-right:20px;border-top:#ddd 1px solid;border-right:#ddd 1px solid;padding-top:20px\">\n" +
                        "                    <p>" + message.getContent() + "</p>\n" +
                        "                </center><p></p><br/>"+
                        "                <div style=\"text-align: center;margin-top: 40px;\">\n" +
                        "                    <img src=\"https://ae01.alicdn.com/kf/U0968ee80fd5c4f05a02bdda9709b041eE.png\" style=\"width:100%;margin:5px auto 5px auto;display: block;\" />\n" +
                        "                    <a style=\" text-transform: uppercase;text-decoration: none;font-size: 14px;border: 2px solid #6c7575;color: #2f3333;padding: 10px; display: inline-block;\" target=\"_blank\" href=\"https://xiejiabin.online\" rel=\"noopener\">先谢郭嘉｜传送！Biu~</a>\n" +
                        "                </div>&nbsp; &nbsp;"+
                        "                <p style=\"font-size: 12px;text-align: center;color: #999;\" id=\"hitokoto\">欢迎常来做客哦！</p>\n" +
                        "                <p style=\"font-size: 12px;text-align: center;color: #999;\">© 2020 <a style=\"text-decoration:none; color:orange\" href=\"https://xiejiabin.online\" rel=\"noopener\" target=\"_blank\"></a> 先谢郭嘉 </p><p></p>\n" +
                        "            <br/></div></div></div>";

                info.setContent(st);

                try {
                    MailSendUtil.sendHtmlMail(info);
                } catch (Exception e) {
                    System.out.print("邮件发送失败！");
                    e.printStackTrace();
                }
            }

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

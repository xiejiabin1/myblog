package com.xie.myblog.controller;

import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.Comment;
import com.xie.myblog.po.Message;
import com.xie.myblog.service.CommentService;
import com.xie.myblog.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/30 13:49
 */
@RestController
@RequestMapping("message")
public class MessageController {
    @Resource
    private MessageService messageService;

    /**
     * 查询所有留言
     * @return
     */
    @GetMapping("listMessage")
    public BtoResult<List<Message>> listMessage() {
        return messageService.listMessage();
    }

    /**
     * 添加留言
     * @param message
     * @return
     */
    @PostMapping("addMessage")
    public boolean addMessage(Message message,Long typeId){
        return messageService.addMessage(message,typeId);
    }

    /**
     * 查询留言次数
     * @return
     */
    @GetMapping("selectCount")
    public BtoResult<Integer> selectCount(){
        return messageService.selectCount();
    }
}

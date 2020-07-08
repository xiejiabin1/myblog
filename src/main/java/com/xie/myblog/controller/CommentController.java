package com.xie.myblog.controller;

import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.Comment;
import com.xie.myblog.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/30 13:49
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @PostMapping("addComment")
    public boolean addComment(Comment comment,Long typeId){
        return commentService.addComment(comment,typeId);
    }

    /**
     * 查询评论次数
     * @return
     */
    @GetMapping("selectCount")
    public BtoResult<Integer> selectCount(){
        return commentService.selectCount();
    }
}

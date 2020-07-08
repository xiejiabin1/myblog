package com.xie.myblog.service.impl;

import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.dao.CommentDao;
import com.xie.myblog.po.Comment;
import com.xie.myblog.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/30 12:40
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplayList = new ArrayList<>();

    /**
     * 查询评论
     * @param blogId
     * @return
     */
    @Transactional
    @Override
    public List<Comment> listComment(Long blogId) {
        //查询所有评论（父评论id为0）
        List<Comment> commentsList = commentDao.selectCommentByParent(Long.parseLong("0"),blogId);
        for(Comment comment : commentsList){
            Long commentId = comment.getCommentId();
            List<Comment> childCommentsList  = commentDao.selectReplayOne(commentId,blogId);
            //查询子评论
            selectChildComment(childCommentsList ,blogId);
            comment.setReplyCommentList(tempReplayList);
            tempReplayList = new ArrayList<>();
        }
        return commentsList;
    }

    /**
     * 查询出子评论
     * @param childCommentsList
     * @param blogId
     */
    private void selectChildComment(List<Comment> childCommentsList ,Long blogId){
        //判断该评论是否有一级子回复
        if(childCommentsList .size() > 0){
            //循环找出子评论的id
            for(Comment childComment : childCommentsList ){
                childComment.setParentComment(selectCommentById(childComment.getParentCommentId()));
                tempReplayList.add(childComment);
                Long childCommentId = childComment.getCommentId();
                //查询二级以及所有子集回复
                selectGrandchildComment(childCommentId,blogId);
            }
        }
    }

    /**
     * 循环迭代找出子集回复
     * @param childCommentId
     * @param blogId
     */
    private void selectGrandchildComment(Long childCommentId, Long blogId){
        //根据子一级评论的id找到子二级评论
        List<Comment> replayCommentsList = commentDao.selectReplayTwoList(childCommentId,blogId);

        if(replayCommentsList.size() > 0){
            for(Comment replayComment : replayCommentsList){
                Long replayCommentId = replayComment.getCommentId();
                replayComment.setParentComment(selectCommentById(replayComment.getParentCommentId()));
                tempReplayList.add(replayComment);
                //循环迭代找出子集回复
                selectGrandchildComment(replayCommentId,blogId);
            }
        }
    }

    @Override
    public boolean addComment(Comment comment,Long typeId) {
        comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if(typeId == 1){
            comment.setAdminComment(true);
        }else {
            comment.setAdminComment(false);
        }
        return commentDao.addComment(comment)>0 ? true : false;
    }

    @Override
    public Comment selectCommentById(Long commentId) {
        return commentDao.selectCommentById(commentId);
    }

    @Override
    public BtoResult<Integer> selectCount() {
        return new BtoResult<>(true,"查询成功",commentDao.selectCount());
    }

}

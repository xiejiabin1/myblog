package com.xie.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.dao.BlogDao;
import com.xie.myblog.dao.CommentDao;
import com.xie.myblog.po.Blog;
import com.xie.myblog.service.BlogService;
import com.xie.myblog.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/29 20:18
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogDao blogDao;

    @Resource
    private CommentService commentService;

    @Override
    public BtoResult<PageInfo<Blog>> selectBlogAll(Integer pageNum, Integer pageSize, Boolean recommend,
                                                   Boolean published, Long typeId, String searchStr) {
        Map<String,Object> map = new HashMap<>();
        if(pageNum == null){
            pageNum = 1;
        }
        if(typeId != null){
            map.put("typeId",typeId);
        }
        map.put("recommend",recommend);
        map.put("published",published);
        if(searchStr != null){
            map.put("searchStr",searchStr);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> list = blogDao.selectBlogAll(map);
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        return new BtoResult<>(true,"查询成功",pageInfo);
    }

    @Override
    public BtoResult<List<Blog>> selectRecommend() {
        return new BtoResult<>(true,"查询成功",blogDao.selectRecommend());
    }

    @Override
    public BtoResult<PageInfo<Blog>> selectPublished(Integer pageNum, Integer pageSize,Long typeId) {
        if(pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> list = blogDao.selectPublished(typeId);
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        return new BtoResult<>(true,"查询成功",pageInfo);
    }

    @Override
    public BtoResult<List<Blog>> searchBlog(String searchStr) {
        return new BtoResult<>(true,"查询成功",blogDao.searchBlog(searchStr));
    }

    @Transactional
    @Override
    public boolean addBlog(Blog blog) {
        blog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return blogDao.addBlog(blog)>0 ? true : false;
    }

    @Transactional
    @Override
    public boolean updateBlog(Blog blog) {
        blog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return blogDao.updateBlog(blog)>0 ? true : false;
    }

    @Override
    public boolean delBlog(Long blogId) {
        return blogDao.delBlog(blogId)>0 ? true : false;
    }

    @Override
    public BtoResult<Integer> blogCount(Long typeId,String searchStr) {
        return new BtoResult<>(true,"查询成功",blogDao.blogCount(typeId,searchStr));
    }

    @Override
    public boolean addViews(Long blogId) {
        return blogDao.addViews(blogId)>0 ? true : false;
    }

    @Override
    public boolean addCommentCount(Long blogId) {
        return blogDao.addCommentCount(blogId)>0 ? true : false;
    }

    @Transactional
    @Override
    public BtoResult<Blog> selectBlogById(Long blogId) {
        Blog blog = blogDao.selectBlogById(blogId);
        blog.setCommentList(commentService.listComment(blogId));
        return new BtoResult<>(true,"查询成功",blog);
    }

    @Override
    public BtoResult<List<Blog>> selectArchives() {
        return new BtoResult<>(true,"查询成功",blogDao.selectArchives());
    }
}

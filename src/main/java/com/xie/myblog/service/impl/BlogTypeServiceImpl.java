package com.xie.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.dao.BlogDao;
import com.xie.myblog.dao.BlogTypeDao;
import com.xie.myblog.po.BlogType;
import com.xie.myblog.po.UserType;
import com.xie.myblog.service.BlogTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/30 19:04
 */
@Service
public class BlogTypeServiceImpl implements BlogTypeService {
    @Resource
    private BlogTypeDao blogTypeDao;
    @Resource
    private BlogDao blogDao;

    @Override
    public BtoResult<List<BlogType>> selectBlogType() {
        return new BtoResult<>(true,"查询",blogTypeDao.selectBlogType(""));
    }

    @Transactional
    @Override
    public BtoResult<List<BlogType>> selectBlogTypeToCount() {
        List<BlogType> list = blogTypeDao.selectBlogTypeToCount();
        for(BlogType blogType : list){
            blogType.setBlogCount(blogDao.blogCount(blogType.getId(),""));
        }
        return new BtoResult<>(true,"查询",list);
    }

    @Override
    public BtoResult<PageInfo<BlogType>> selectBlogType(Integer pageNum,Integer pageSize,String name) {
        if(pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<BlogType> list = blogTypeDao.selectBlogType(name);
        PageInfo<BlogType> pageInfo = new PageInfo<>(list);
        return new BtoResult<>(true,"查询成功",pageInfo);
    }

    @Override
    public boolean addBlogType(BlogType blogType) {
        return blogTypeDao.addBlogType(blogType)>0 ? true : false;
    }

    @Override
    public boolean delBlogType(Long id) {
        return blogTypeDao.delBlogType(id)>0 ? true : false;
    }

    @Override
    public boolean updateBlogType(BlogType blogType) {
        return blogTypeDao.updateBlogType(blogType)>0 ? true : false;
    }

    @Override
    public boolean isName(String name) {
        return blogTypeDao.isName(name)>0 ? true : false;
    }
}

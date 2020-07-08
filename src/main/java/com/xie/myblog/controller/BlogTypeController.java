package com.xie.myblog.controller;

import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.BlogType;
import com.xie.myblog.service.BlogTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/30 19:20
 */
@RestController
@RequestMapping("blogType")
public class BlogTypeController {
    @Resource
    private BlogTypeService blogTypeService;

    /**
     * 查询所有博客分类
     * @return
     */
    @GetMapping("selectBlogType")
    public BtoResult<List<BlogType>> selectBlogType(){
        return blogTypeService.selectBlogType();
    }

    /**
     * 查询所有博客分类
     * @return
     */
    @GetMapping("selectBlogTypeToCount")
    public BtoResult<List<BlogType>> selectBlogTypeToCount() {
        return blogTypeService.selectBlogTypeToCount();
    }

    /**
     * 分页模糊查询所有博客分类
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("selectBlogTypeLimit")
    public BtoResult<PageInfo<BlogType>> selectBlogType(Integer pageNum, Integer pageSize, String name){
        return blogTypeService.selectBlogType(pageNum,pageSize,name);
    }

    /**
     * 新增分类
     * @param blogType
     * @return
     */
    @PostMapping("addBlogType")
    public boolean addBlogType(BlogType blogType){
        return blogTypeService.addBlogType(blogType);
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @PostMapping("delBlogType")
    public boolean delBlogType(Long id){
        return blogTypeService.delBlogType(id);
    }

    /**
     * 修改分类
     * @param blogType
     * @return
     */
    @PostMapping("updateBlogType")
    public boolean updateBlogType(BlogType blogType){
        return blogTypeService.updateBlogType(blogType);
    }

    /**
     * 判断该分类名称是否存在
     * @param name
     * @return
     */
    @GetMapping("isName")
    public boolean isName(String name) {
        return blogTypeService.isName(name);
    }
}

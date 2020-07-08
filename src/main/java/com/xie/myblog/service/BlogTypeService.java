package com.xie.myblog.service;

import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.BlogType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogTypeService {
    /**
     * 查询所有博客分类
     * @return
     */
    BtoResult<List<BlogType>> selectBlogType();

    /**
     * 查询所有博客分类
     * @return
     */
    BtoResult<List<BlogType>> selectBlogTypeToCount();

    /**
     * 分页模糊查询所有博客分类
     * @param name
     * @return
     */
    BtoResult<PageInfo<BlogType>> selectBlogType(Integer pageNum,Integer pageSize,String name);

    /**
     * 新增分类
     * @param blogType
     * @return
     */
    boolean addBlogType(BlogType blogType);

    /**
     * 删除分类
     * @param id
     * @return
     */
    boolean delBlogType(Long id);

    /**
     * 修改分类
     * @param blogType
     * @return
     */
    boolean updateBlogType(BlogType blogType);

    /**
     * 判断该分类名称是否存在
     * @param name
     * @return
     */
    boolean isName(String name);
}

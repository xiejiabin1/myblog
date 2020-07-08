package com.xie.myblog.dao;

import com.xie.myblog.po.BlogType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogTypeDao {

    /**
     * 模糊查询博客分类
     * @return
     */
    List<BlogType> selectBlogType(@Param("name") String name);

    /**
     * 查询所有博客分类
     * @return
     */
    List<BlogType> selectBlogTypeToCount();

    /**
     * 新增分类
     * @param blogType
     * @return
     */
    int addBlogType(BlogType blogType);

    /**
     * 删除分类
     * @param id
     * @return
     */
    int delBlogType(@Param("id") Long id);

    /**
     * 修改分类
     * @param blogType
     * @return
     */
    int updateBlogType(BlogType blogType);

    /**
     * 判断该分类名称是否存在
     * @param name
     * @return
     */
    int isName(@Param("name") String name);
}

package com.xie.myblog.dao;

import com.xie.myblog.po.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogDao {

    /**
     * 条件模糊查询所有文章
     * @param map
     * @return
     */
    List<Blog> selectBlogAll(Map<String,Object> map);

    /**
     * 查询4篇推荐博文
     * @return
     */
    List<Blog> selectRecommend();

    /**
     * 分页查询已发布的博文
     * @return
     */
    List<Blog> selectPublished(@Param("typeId") Long typeId);

    /**
     * 模糊查询已发布的博文
     * @param searchStr
     * @return
     */
    List<Blog> searchBlog(@Param("searchStr") String searchStr);


    /**
     * 新增文章
     * @param blog
     * @return
     */
    int addBlog(Blog blog);

    /**
     * 修改文章
     * @param blog
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * 根据文章id删除文章
     * @return
     */
    int delBlog(@Param("blogId") Long blogId);

    /**
     * 条件查询博客文章总数量
     * @return
     */
    Integer blogCount(@Param("typeId") Long typeId,@Param("searchStr") String searchStr);

    /**
     * 增加文章浏览次数
     * @return
     */
    int addViews(@Param("blogId") Long blogId);

    /**
     * 增加博客文章评价次数
     * @return
     */
    int addCommentCount(@Param("blogId") Long blogId);

    /**
     * 根据比克id查询该博客
     * @param blogId
     * @return
     */
    Blog selectBlogById(@Param("blogId") Long blogId);

    /**
     * 查询时间轴
     * @return
     */
    List<Blog> selectArchives();

}

package com.xie.myblog.service;

import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BlogService {
    /**
     * 条件模糊查询所有文章
     * @param pageNum
     * @param pageSize
     * @param recommend
     * @param typeId
     * @param searchStr
     * @return
     */
    BtoResult<PageInfo<Blog>> selectBlogAll(Integer pageNum,Integer pageSize,Boolean recommend,
                                            Boolean published,Long typeId,String searchStr);

    /**
     * 查询4篇推荐博文
     * @return
     */
    BtoResult<List<Blog>> selectRecommend();

    /**
     * 分页查询已发布的博文
     * @param pageNum
     * @param pageSize
     * @return
     */
    BtoResult<PageInfo<Blog>> selectPublished(Integer pageNum, Integer pageSize,Long typeId);

    /**
     * 模糊查询已发布的博文
     * @param searchStr
     * @return
     */
    BtoResult<List<Blog>> searchBlog(String searchStr);

    /**
     * 新增文章
     * @param blog
     * @return
     */
    boolean addBlog(Blog blog);

    /**
     * 修改文章
     * @param blog
     * @return
     */
    boolean updateBlog(Blog blog);

    /**
     * 根据文章id删除文章
     * @return
     */
    boolean delBlog(Long blogId);

    /**
     * 查询博客文章总数量
     * @return
     */
    BtoResult<Integer> blogCount(Long typeId,String searchStr);

    /**
     * 增加文章浏览次数
     * @return
     */
    boolean addViews(Long blogId);

    /**
     * 增加博客文章评价次数
     * @return
     */
    boolean addCommentCount(Long blogId);

    /**
     * 根据比克id查询该博客
     * @param blogId
     * @return
     */
    BtoResult<Blog> selectBlogById(Long blogId);

    /**
     * 查询时间轴
     * @return
     */
    BtoResult<List<Blog>> selectArchives();

}

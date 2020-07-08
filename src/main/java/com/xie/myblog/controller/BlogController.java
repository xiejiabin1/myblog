package com.xie.myblog.controller;

import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.Blog;
import com.xie.myblog.service.BlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/29 20:24
 */
@RestController
@RequestMapping("blog")
public class BlogController {
    @Resource
    private BlogService blogService;

    /**
     * 条件模糊查询所有文章（admin）
     * @param pageNum
     * @param pageSize
     * @param recommend
     * @param typeId
     * @param searchStr
     * @return
     */
    @GetMapping("selectBlogAll")
    public BtoResult<PageInfo<Blog>> selectBlogAll(Integer pageNum, Integer pageSize,Boolean recommend,
                                                   Boolean published,Long typeId, String searchStr){
        return blogService.selectBlogAll(pageNum,pageSize,recommend,published,typeId,searchStr);
    }

    /**
     * 查询4篇推荐博文
     * @return
     */
    @GetMapping("selectRecommend")
    public BtoResult<List<Blog>> selectRecommend() {
        return blogService.selectRecommend();
    }

    /**
     * 分页查询已发布的博文
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("selectPublished")
    public BtoResult<PageInfo<Blog>> selectPublished(Integer pageNum, Integer pageSize,Long typeId) {
        return blogService.selectPublished(pageNum,pageSize,typeId);
    }

    /**
     * 模糊查询博客文章
     * @return
     */
    @GetMapping("searchBlog")
    public BtoResult<List<Blog>> searchBlog(String searchStr) {
        return blogService.searchBlog(searchStr);
    }

    /**
     * 新增文章
     * @param blog
     * @return
     */
    @PostMapping("addBlog")
    public boolean addBlog(Blog blog){
        return blogService.addBlog(blog);
    }

    /**
     * 修改文章
     * @param blog
     * @return
     */
    @PostMapping("updateBlog")
    public boolean updateBlog(Blog blog){
        return blogService.updateBlog(blog);
    }

    /**
     * 根据文章id删除文章
     * @return
     */
    @PostMapping("delBlog")
    public boolean delBlog(Long blogId){
        return blogService.delBlog(blogId);
    }

    /**
     * 查询博客文章总数量
     * @return
     */
    @GetMapping("blogCount")
    public BtoResult<Integer> blogCount(@RequestParam(value = "typeId", required = false) Long typeId,
                                        @RequestParam(value = "searchStr", required = false) String searchStr){
        return blogService.blogCount(typeId,searchStr);
    }

    /**
     * 增加文章浏览次数
     * @return
     */
    @PostMapping("addViews")
    public boolean addViews(Long blogId){
        return blogService.addViews(blogId);
    }

    /**
     * 增加博客文章评价次数
     * @return
     */
    @PostMapping("addCommentCount")
    public boolean addCommentCount(Long blogId){
        return blogService.addCommentCount(blogId);
    }

    /**
     * 根据博客id查询该博客
     * @param blogId
     * @return
     */
    @GetMapping("selectBlogById")
    public BtoResult<Blog> selectBlogById(Long blogId){
        return blogService.selectBlogById(blogId);
    }

    /**
     * 查询时间轴
     * @return
     */
    @GetMapping("selectArchives")
    public BtoResult<List<Blog>> selectArchives() {
        return blogService.selectArchives();
    }
}

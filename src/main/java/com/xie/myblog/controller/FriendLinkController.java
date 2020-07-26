package com.xie.myblog.controller;

import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.FriendLink;
import com.xie.myblog.service.FriendLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/28 13:04
 */
@RestController
@RequestMapping("friendLink")
@Api(tags = {"友链controller"})
public class FriendLinkController {
    @Resource
    private FriendLinkService friendLinkService;

    /**
     * 分页查询所有友链
     * @param pageNum
     * @param pageSize
     * @param blogName
     * @return
     */
    @GetMapping("selectFriendAll")
    @ApiOperation(value = "分页查询所有友链")
    public BtoResult<PageInfo<FriendLink>> selectFriendAll(Integer pageNum, Integer pageSize, String blogName){
        return friendLinkService.selectFriendAll(pageNum,pageSize,blogName);
    }

    /**
     * 查询所有友链
     * @return
     */
    @GetMapping("selectFriend")
    @ApiOperation(value = "查询所有友链")
    public BtoResult<List<FriendLink>> selectFriendAll(){
        return friendLinkService.selectFriendAll();
    }

    /**
     * 新增友链
     * @param friendLink
     * @return
     */
    @PostMapping("addFriendLink")
    @ApiOperation(value = "新增友链")
    public boolean addFriendLink(FriendLink friendLink){
        return friendLinkService.addFriendLink(friendLink);
    }

    /**
     * 删除友链
     * @param friendLinkId
     * @return
     */
    @PostMapping("delFriendLink")
    @ApiOperation(value = "删除友链")
    public boolean delFriendLink(Long friendLinkId){
        return friendLinkService.delFriendLink(friendLinkId);
    }

    /**
     * 修改友链
     * @param friendLink
     * @return
     */
    @PostMapping("updateFriendLink")
    @ApiOperation(value = "修改友链")
    public boolean updateFriendLink(FriendLink friendLink){
        return friendLinkService.updateFriendLink(friendLink);
    }

    /**
     * 根据ID查询
     * @param friendLinkId
     * @return
     */
    @GetMapping("selectLinkById")
    @ApiOperation(value = "根据ID查询")
    public BtoResult<FriendLink> selectLinkById(Long friendLinkId) {
        return friendLinkService.selectLinkById(friendLinkId);
    }
}

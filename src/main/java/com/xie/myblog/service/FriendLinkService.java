package com.xie.myblog.service;

import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.FriendLink;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendLinkService {
    /**
     * 查询所有友链
     * @return
     */
    BtoResult<PageInfo<FriendLink>> selectFriendAll(Integer pageNum,Integer pageSize,String blogName);

    BtoResult<List<FriendLink>> selectFriendAll();

    /**
     * 新增友链
     * @param friendLink
     * @return
     */
    boolean addFriendLink(FriendLink friendLink);

    /**
     * 删除友链
     * @param friendLinkId
     * @return
     */
    boolean delFriendLink(Long friendLinkId);

    /**
     * 修改友链
     * @param friendLink
     * @return
     */
    boolean updateFriendLink(FriendLink friendLink);

    BtoResult<FriendLink> selectLinkById( Long friendLinkId);
}

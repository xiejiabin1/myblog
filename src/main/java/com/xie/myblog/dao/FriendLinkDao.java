package com.xie.myblog.dao;

import com.xie.myblog.po.FriendLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface FriendLinkDao {

    /**
     * 查询所有友链
     * @return
     */
    List<FriendLink> selectFriendAll(@Param("blogName") String blogName);

    /**
     * 新增友链
     * @param friendLink
     * @return
     */
    int addFriendLink(FriendLink friendLink);

    /**
     * 删除友链
     * @param friendLinkId
     * @return
     */
    int delFriendLink(@Param("friendLinkId") Long friendLinkId);

    /**
     * 修改友链
     * @param friendLink
     * @return
     */
    int updateFriendLink(FriendLink friendLink);

    /**
     * 根据id查询
     * @param friendLinkId
     * @return
     */
    FriendLink selectLinkById(@Param("friendLinkId") Long friendLinkId);
}

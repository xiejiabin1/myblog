package com.xie.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.dao.FriendLinkDao;
import com.xie.myblog.po.BlogType;
import com.xie.myblog.po.FriendLink;
import com.xie.myblog.service.FriendLinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/28 13:02
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {
    @Resource
    private FriendLinkDao friendLinkDao;

    @Override
    public BtoResult<PageInfo<FriendLink>> selectFriendAll(Integer pageNum,Integer pageSize,String blogName) {
        if(pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<FriendLink> list = friendLinkDao.selectFriendAll(blogName);
        PageInfo<FriendLink> pageInfo = new PageInfo<>(list);
        return new BtoResult<>(true,"查询成功",pageInfo);
    }

    @Override
    public BtoResult<List<FriendLink>> selectFriendAll() {
        return new BtoResult<>(true,"查询成功",friendLinkDao.selectFriendAll(""));
    }

    @Transactional
    @Override
    public boolean addFriendLink(FriendLink friendLink) {
        friendLink.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return friendLinkDao.addFriendLink(friendLink)>0 ? true : false;
    }

    @Transactional
    @Override
    public boolean delFriendLink(Long friendLinkId) {
        return friendLinkDao.delFriendLink(friendLinkId)>0 ? true : false;
    }

    @Transactional
    @Override
    public boolean updateFriendLink(FriendLink friendLink) {
        return friendLinkDao.updateFriendLink(friendLink)>0 ? true : false;
    }

    @Override
    public BtoResult<FriendLink> selectLinkById(Long friendLinkId) {
        return new BtoResult<>(true,"查询成功",friendLinkDao.selectLinkById(friendLinkId));
    }
}

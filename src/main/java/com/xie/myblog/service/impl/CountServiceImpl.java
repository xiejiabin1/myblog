package com.xie.myblog.service.impl;

import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.dao.CountDao;
import com.xie.myblog.po.Count;
import com.xie.myblog.service.CountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: 谢
 * @time: 2020/7/3 10:14
 */
@Service
public class CountServiceImpl implements CountService {

    @Resource
    private CountDao countDao;

    @Override
    public BtoResult<Count> selectCount() {
        return new BtoResult<>(true,"查询成功",countDao.selectCount());
    }

    @Override
    public boolean addCount() {
        return countDao.addCount()>0 ? true : false;
    }
}

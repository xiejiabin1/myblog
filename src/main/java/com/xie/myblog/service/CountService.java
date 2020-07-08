package com.xie.myblog.service;

import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.Count;

/**
 * @description:
 * @author: 谢
 * @time: 2020/7/3 10:13
 */
public interface CountService {
    /**
     * 查询网站访问次数
     * @return
     */
    BtoResult<Count> selectCount();

    /**
     * 增加访问次数
     * @return
     */
    boolean addCount();
}

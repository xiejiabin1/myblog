package com.xie.myblog.dao;

import com.xie.myblog.po.Count;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CountDao {

    /**
     * 查询网站访问次数
     * @return
     */
    Count selectCount();

    /**
     * 增加访问次数
     * @return
     */
    int addCount();
}

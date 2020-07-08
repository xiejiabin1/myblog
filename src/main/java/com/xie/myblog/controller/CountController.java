package com.xie.myblog.controller;

import com.xie.myblog.bto.BtoResult;
import com.xie.myblog.po.Count;
import com.xie.myblog.service.CountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: 谢
 * @time: 2020/7/3 10:16
 */
@RestController
@RequestMapping("count")
public class CountController {
    @Resource
    private CountService countService;

    /**
     * 查询网站访问次数
     * @return
     */
    @GetMapping("selectCount")
    public BtoResult<Count> selectCount(){
        return countService.selectCount();
    }
}

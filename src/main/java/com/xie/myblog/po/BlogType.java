package com.xie.myblog.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/26 12:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BlogType implements Serializable {
    private Long id;            //博客分类id
    private String name;        //博客分类名称

    private Integer blogCount;  //该分类下的博客文章数量
}

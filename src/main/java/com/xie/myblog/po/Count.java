package com.xie.myblog.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: 谢
 * @time: 2020/7/3 10:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Count {
    private Long id;
    private Long counts;     //网站访问次数
}

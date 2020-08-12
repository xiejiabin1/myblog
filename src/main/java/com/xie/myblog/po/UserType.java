package com.xie.myblog.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description: 角色实体类
 * @author: 谢
 * @time: 2020/6/26 12:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserType implements Serializable {
    private Long id;         //用户角色id
    private String name;     //用户角色名称
}

package com.xie.myblog.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/26 12:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Picture implements Serializable {
    private Long pictureId;             //图片id
    private String pictureName;         //图片名称
    private String pictureAddress;      //图片地址
    private String pictureDescription;  //图片描述
}

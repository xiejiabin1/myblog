package com.xie.myblog.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description:
 * @author: 谢
 * @time: 2020/7/16 16:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MailInfo implements Serializable {
    //邮箱服务器 如smtp.163.com
    private String host ;
    //用户邮箱 如**@163
    private String formName ;
    //用户授权码 不是用户名密码 可以自行查看相关邮件服务器怎么查看
    private String formPassword ;
    //消息回复邮箱
    private String replayAddress ;
    //发送地址
    private String toAddress ;
    //发送主题
    private String subject ;
    //发送内容
    private String content ;
}

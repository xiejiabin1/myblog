package com.xie.myblog.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 留言实体类
 * @author: 谢
 * @time: 2020/6/26 12:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Message implements Serializable {
    private Long messageId;             //留言id
    private Long userId;                //用户id
    private Long parentMessageId;       //父留言id
    private String content;             //留言内容
    private Boolean adminMessage;       //是否为管理员留言
    private Timestamp createTime;       //创建时间

    private User user;
    private List<Message> replyMessagesList = new ArrayList<>();//回复留言
    private Message parentMessage;      //父留言
}

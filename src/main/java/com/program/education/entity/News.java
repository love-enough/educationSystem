package com.program.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName news
 */
@TableName(value ="news")
@Data
public class News implements Serializable {
    /**
     * 新闻id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发布人
     */
    @TableField(value = "admin_id")
    private Integer admin_id;

    /**
     * 新闻标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 新闻内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
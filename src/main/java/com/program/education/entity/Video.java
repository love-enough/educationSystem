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
 * @TableName video
 */
@TableName(value ="video")
@Data
public class Video implements Serializable {
    /**
     * 视频id
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 发布教师id
     */
    @TableField(value = "teacher_id")
    private Integer teacher_id;

    /**
     * 视频描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 视频url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date create_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
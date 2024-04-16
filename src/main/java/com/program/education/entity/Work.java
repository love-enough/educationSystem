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
 * @TableName work
 */
@TableName(value ="work")
@Data
public class Work implements Serializable {
    /**
     * 作业id
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 发布教师id
     */
    @TableField(value = "teacher_id")
    private Integer teacher_id;

    /**
     * 作业标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 作业内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 作业文件
     */
    @TableField(value = "file")
    private String file;

    /**
     * 发布时间
     */
    @TableField(value = "create_time")
    private Date create_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
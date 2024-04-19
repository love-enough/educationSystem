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
 * @TableName upload
 */
@TableName(value ="upload")
@Data
public class Upload implements Serializable {
    /**
     * 文件id
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 作业id
     */
    @TableField(value = "work_id")
    private Integer work_id;

    /**
     * 提交用户
     */
    @TableField(value = "user_id")
    private Integer user_id;

    /**
     * 上传文件
     */
    @TableField(value = "file")
    private String file;

    /**
     * 上传时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
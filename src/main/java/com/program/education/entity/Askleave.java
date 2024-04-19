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
 * @TableName askleave
 */
@TableName(value ="askleave")
@Data
public class Askleave implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 请假人
     */
    @TableField(value = "user_id")
    private Integer user_id;

    /**
     * 处理教师id
     */
    @TableField(value = "teacher_id")
    private Integer teacher_id;

    /**
     * 请假理由
     */
    @TableField(value = "content")
    private String content;

    /**
     * 起始时间
     */
    @TableField(value = "fromTime")
    private Date fromTime;

    /**
     * 终止时间
     */
    @TableField(value = "toTime")
    private Date toTime;

    /**
     * 申请时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 0 未批准 1 批准 2驳回
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
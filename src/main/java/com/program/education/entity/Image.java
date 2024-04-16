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
 * @TableName image
 */
@TableName(value ="image")
@Data
public class Image implements Serializable {
    /**
     * 轮播图编号
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 轮播图地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 1 启用 0 下线
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 上传时间
     */
    @TableField(value = "create_time")
    private Date create_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
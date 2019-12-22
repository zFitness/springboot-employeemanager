package cn.employee.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 人事变动记录表
 * @author zfitness
 */
@Data
public class Personnel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer person;
    @TableField("change_id")
    //踩坑， 数据库的字段不能叫 change
    private Integer changeId;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date time;
}

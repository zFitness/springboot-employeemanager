package cn.employee.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 员工实体类
 * 员工表
 * @author zfitness
 */
@TableName("employee")
@Data
public class Employee {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String password;
    private String name;
    private Integer authority;
    private int sex;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthday;
    private Integer department;
    private Integer job;
    @TableField("edu_level")
    private Integer eduLevel;
    private String spcialty;
    private String address;
    private String tel;
    private String email;
    private String state;
    private String remark;
    private String openid;
}

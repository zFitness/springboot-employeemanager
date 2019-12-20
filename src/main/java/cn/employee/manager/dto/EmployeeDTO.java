package cn.employee.manager.dto;

import cn.employee.manager.entity.Employee;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zfitness
 */
@Data
public class EmployeeDTO {
    private Integer id;
    private String password;
    private String name;
    private Integer authority;
    private int sex;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthday;
    //    部门
    private String department;
    //    工作
    private String job;
    //教育程度
    private String eduLevel;
    private String spcialty;
    private String address;
    private String tel;
    private String email;
    private String state;
    private String remark;
}

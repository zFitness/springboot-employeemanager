package cn.employee.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 部门表
 * @author zfitness
 */
@Data
public class Department {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String intro;
}

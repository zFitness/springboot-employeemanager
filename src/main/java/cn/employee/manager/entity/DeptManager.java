package cn.employee.manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 部门-经理表对应的实体类
 * @author zfitness
 */
@Data
@TableName("dept_manager")
public class DeptManager {
    @TableId
    private Integer id;
    @TableField("dept_id")
    private Integer deptId;
    @TableField("emp_id")
    private Integer empId;
}

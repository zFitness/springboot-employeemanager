package cn.employee.manager.dto;

import cn.employee.manager.entity.Employee;
import lombok.Data;

/**
 * @author zfitness
 */
@Data
public class DepartmentDTO {
    private Integer id;
    private String name;
    private String intro;
    private EmployeeDTO manager;
}

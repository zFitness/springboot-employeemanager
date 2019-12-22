package cn.employee.manager.service;

import cn.employee.manager.dto.EmployeeDTO;
import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.Employee;

import java.util.Map;

public interface EmployeeService {

    Result list(Integer current, Integer size);

    Result deleteEmployeeById(Integer id);

    EmployeeDTO getUserById(Integer id);

    /**
     * 插入新员工
     * @param employee
     * @return
     */
    Map<String, Object> add(Employee employee);
}

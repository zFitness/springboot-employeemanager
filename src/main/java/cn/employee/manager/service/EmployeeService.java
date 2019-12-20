package cn.employee.manager.service;

import cn.employee.manager.dto.result.Result;

public interface EmployeeService {

    Result list(Integer current, Integer size);

    Result deleteEmployeeById(Integer id);
}

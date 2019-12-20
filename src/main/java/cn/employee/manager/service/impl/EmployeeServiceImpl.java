package cn.employee.manager.service.impl;

import cn.employee.manager.dto.EmployeeDTO;
import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.Employee;
import cn.employee.manager.mapper.EmployeeMapper;
import cn.employee.manager.service.EmployeeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zfitness
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Result list(Integer current, Integer size) {
        Page<EmployeeDTO> page = new Page<>(current, size);
        return Result.success(employeeMapper.getEmployeeByPage(page));
    }

    @Override
    public Result deleteEmployeeById(Integer id) {
        int i = employeeMapper.deleteById(id);
        if (i == 0) {
            return Result.failure();
        } else {
            return Result.success();
        }
    }
}

package cn.employee.manager.service.impl;

import cn.employee.manager.entity.Employee;
import cn.employee.manager.mapper.EmployeeMapper;
import cn.employee.manager.service.AuthService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zfitness
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> login(String email, String password) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getEmail, email);
        queryWrapper.eq(Employee::getPassword, password);
        return employeeMapper.selectList(queryWrapper);
    }
}

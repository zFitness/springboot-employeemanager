package cn.employee.manager.service.impl;

import cn.employee.manager.dto.LoginDTO;
import cn.employee.manager.dto.result.ResponseCode;
import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.Employee;
import cn.employee.manager.mapper.EmployeeMapper;
import cn.employee.manager.service.AuthService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author zfitness
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Result login(String email, String password) {

        //先用邮箱查询是否有这个员工
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getEmail, email);
//        queryWrapper.eq(Employee::getPassword, password);
        Employee employee = employeeMapper.selectOne(queryWrapper);
        //判断员工是否存在
        if (employee == null) {
            return Result.failure(ResponseCode.USER_NOT_FOUND);
        } else {
            //判断密码是否正确
            if (!employee.getPassword().equals(password)) {
                return Result.failure(ResponseCode.PASSWORD_ERROR);
            }
            // 生成token
            String token = UUID.randomUUID().toString();
            LoginDTO loginDTO = new LoginDTO();
            if (employee.getAuthority() == 1) {
                loginDTO.setSuper(true);
            } else {
                loginDTO.setSuper(false);
            }
            loginDTO.setToken(token);
            loginDTO.setUserId(employee.getId());
            return Result.success(loginDTO);
        }
    }
}

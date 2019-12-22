package cn.employee.manager.service.impl;

import cn.employee.manager.dto.EmployeeDTO;
import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.Employee;
import cn.employee.manager.entity.Personnel;
import cn.employee.manager.mapper.EmployeeMapper;
import cn.employee.manager.mapper.PersonnelMapper;
import cn.employee.manager.service.EmployeeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zfitness
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PersonnelMapper personnelMapper;

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

    @Override
    public EmployeeDTO getUserById(Integer id) {
        return employeeMapper.getUserById(id);
    }

    @Override
    public Map<String, Object> add(Employee employee) {
        //初始化默认密码
        employee.setPassword("123");
        //设置用户状态
        employee.setState("T");
        int i = employeeMapper.insert(employee);
        Map<String,Object> map = new HashMap<>(3);
        //插入成功，人事表需要插入一条记录
        if (i == 1) {
            Personnel personnel = new Personnel();
            //0代表新员工加入
            personnel.setChangeId(0);
            personnel.setDescription("新来一位大佬");
            personnel.setPerson(employee.getId());
            personnel.setTime(new Date());
            personnelMapper.insert(personnel);
            map.put("code", 200);
            map.put("msg", "插入成功");
            return map;
        } else {
            map.put("code", 500);
            map.put("msg", "插入失败了");
            return map;
        }
    }
}

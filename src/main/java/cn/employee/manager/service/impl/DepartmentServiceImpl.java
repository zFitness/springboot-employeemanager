package cn.employee.manager.service.impl;

import cn.employee.manager.dto.DepartmentDTO;
import cn.employee.manager.dto.EmployeeDTO;
import cn.employee.manager.entity.Department;
import cn.employee.manager.entity.DeptManager;
import cn.employee.manager.entity.Employee;
import cn.employee.manager.mapper.DepartmentMapper;
import cn.employee.manager.mapper.DeptManagerMapper;
import cn.employee.manager.mapper.EmployeeMapper;
import cn.employee.manager.service.DepartmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zfitness
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DeptManagerMapper deptManagerMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<DepartmentDTO> selectAll() {
        List<DepartmentDTO> departmentDTOS = departmentMapper.selectAll();
        for (DepartmentDTO departmentDTO : departmentDTOS) {
            LambdaQueryWrapper<DeptManager> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DeptManager::getDeptId, departmentDTO.getId());
            DeptManager deptManager = deptManagerMapper.selectOne(queryWrapper);
            // fixes bug  #空指针异常,  如果部门没有经理, 会抛出空指针异常
            if (deptManager != null) {
                EmployeeDTO manager = employeeMapper.getUserById(deptManager.getEmpId());
                departmentDTO.setManager(manager);
            }
        }
        return departmentDTOS;
    }

    @Override
    public Map<String, Object> add(Map<String, Object> map) {

        //先查询是否有部门
        String name = (String) map.get("name");
        Integer managerId = Integer.parseInt((String) map.get("managerId"));
        LambdaQueryWrapper<Department> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Department::getName, name);
        Integer count = departmentMapper.selectCount(wrapper);
        if (count != 0) {
            map.put("code", 511);
            map.put("message", "部门存在");
            return map;
        }
        //查询经理是否存在
        LambdaQueryWrapper<Employee> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Employee::getId, managerId);
        Integer count1 = employeeMapper.selectCount(wrapper1);
        if (count1 == 0) {
            map.put("code", 404);
            map.put("message", "用户不存在");
            return map;
        }
        //定义返回结果
        Map<String, Object> resultMap = new HashMap<>();
        Department department = new Department();
        department.setIntro((String) map.get("description"));
        department.setName((String) map.get("name"));
        //插入部门
        int i = departmentMapper.insert(department);
        //部门经理表
        DeptManager deptManager = new DeptManager();
        deptManager.setDeptId(department.getId());
        deptManager.setEmpId(managerId);
        int insert = deptManagerMapper.insert(deptManager);
        if (i !=0 ) {
            map.put("code", 200);
            map.put("message", "添加成功");
            return map;
        } else {
            map.put("code", 500);
            map.put("message", "添加失败");
            return map;
        }
    }
}

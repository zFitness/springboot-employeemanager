package cn.employee.manager.service.impl;

import cn.employee.manager.dto.DepartmentDTO;
import cn.employee.manager.dto.EmployeeDTO;
import cn.employee.manager.entity.DeptManager;
import cn.employee.manager.entity.Employee;
import cn.employee.manager.mapper.DepartmentMapper;
import cn.employee.manager.mapper.DeptManagerMapper;
import cn.employee.manager.mapper.EmployeeMapper;
import cn.employee.manager.service.DepartmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

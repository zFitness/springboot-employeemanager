package cn.employee.manager.service;

import cn.employee.manager.dto.DepartmentDTO;

import java.util.List;

/**
 * @author zfitness
 */
public interface DepartmentService {
    /**
     * 查询所有部门信息
     * @return
     */
    List<DepartmentDTO> selectAll();
}

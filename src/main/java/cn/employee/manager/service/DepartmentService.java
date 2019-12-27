package cn.employee.manager.service;

import cn.employee.manager.dto.DepartmentDTO;

import java.util.List;
import java.util.Map;

/**
 * @author zfitness
 */
public interface DepartmentService {
    /**
     * 查询所有部门信息
     * @return
     */
    List<DepartmentDTO> selectAll();

    /**
     * 插入部门核心业务逻辑
     * @param map
     * @return
     */
    Map<String, Object> add(Map<String, Object> map);
}

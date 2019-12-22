package cn.employee.manager.mapper;

import cn.employee.manager.dto.EmployeeDTO;
import cn.employee.manager.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 操作员工表
 * @author zfitness
 */
@Mapper
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<EmployeeDTO> getEmployeeByPage(Page page);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    EmployeeDTO getUserById(@Param("id") Integer id);
}
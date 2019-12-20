package cn.employee.manager.mapper;

import cn.employee.manager.entity.DeptManager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 操作 部门-经理表
 * @author zfitness
 */
@Mapper
@Repository
public interface DeptManagerMapper extends BaseMapper<DeptManager> {
}

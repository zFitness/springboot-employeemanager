package cn.employee.manager.mapper;

import cn.employee.manager.entity.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 操作工作表
 * @author zfitness
 */
@Mapper
@Repository
public interface JobMapper extends BaseMapper<Job> {
}

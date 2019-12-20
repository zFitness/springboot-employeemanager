package cn.employee.manager.mapper;

import cn.employee.manager.entity.EduLevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 操作学历表
 * @author zfitness
 */
@Mapper
@Repository
public interface EduLevelMapper extends BaseMapper<EduLevel> {
}

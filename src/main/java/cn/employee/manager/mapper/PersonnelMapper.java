package cn.employee.manager.mapper;

import cn.employee.manager.dto.PersonnelDTO;
import cn.employee.manager.entity.Personnel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zfitness
 */
@Mapper
@Repository
public interface PersonnelMapper extends BaseMapper<Personnel> {
    IPage<PersonnelDTO> getPersonnelPage(Page page);
}

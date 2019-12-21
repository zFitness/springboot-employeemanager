package cn.employee.manager.service.impl;

import cn.employee.manager.dto.EmployeeDTO;
import cn.employee.manager.dto.PersonnelDTO;
import cn.employee.manager.dto.result.Result;
import cn.employee.manager.mapper.PersonnelMapper;
import cn.employee.manager.service.PersonnelService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zfitness
 */
@Service
public class PersonnelServiceImpl implements PersonnelService {
    @Autowired
    private PersonnelMapper personnelMapper;

    @Override
    public Result list(Integer current, Integer size) {
        Page<PersonnelDTO> personnelDTOPage = new Page<>(current, size);
        return Result.success(personnelMapper.getPersonnelPage(personnelDTOPage));
    }
}

package cn.employee.manager.service;

import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.Personnel;


/**
 * 人事管理相关业务操作
 * @author zfitness
 */
public interface PersonnelService {
    /**
     * 查看所以记录
     * @param current
     * @param size
     * @return
     */
    Result list(Integer current, Integer size);

    /**
     * 新增一条人事记录
     * @param personnel
     */
    void add(Personnel personnel);
}

package cn.employee.manager.service;

import cn.employee.manager.entity.Job;

import java.util.List;

/**
 * @author zfitness
 */
public interface JobService {
    /**
     * 查看所有工作
     * @return
     */
    List<Job> selectAll();
}

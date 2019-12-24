package cn.employee.manager.service;

import cn.employee.manager.dto.result.Result;
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

    /**
     * 查询每个工作对应的人数
     * @return
     */
    Result getJobPersonNumbers();

    /**
     * 返回所以welcome 的数据
     * @return
     */
    Result welcome();
}

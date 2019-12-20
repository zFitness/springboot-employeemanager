package cn.employee.manager.service.impl;

import cn.employee.manager.entity.Job;
import cn.employee.manager.mapper.JobMapper;
import cn.employee.manager.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zfitness
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobMapper jobMapper;

    @Override
    public List<Job> selectAll() {
        return jobMapper.selectList(null);
    }
}

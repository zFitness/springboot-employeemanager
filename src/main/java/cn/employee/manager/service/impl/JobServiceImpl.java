package cn.employee.manager.service.impl;

import cn.employee.manager.dto.JobPersonNumbers;
import cn.employee.manager.dto.WelcomeDTO;
import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.Employee;
import cn.employee.manager.entity.Job;
import cn.employee.manager.mapper.DepartmentMapper;
import cn.employee.manager.mapper.EmployeeMapper;
import cn.employee.manager.mapper.JobMapper;
import cn.employee.manager.mapper.PersonnelMapper;
import cn.employee.manager.service.JobService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zfitness
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PersonnelMapper personnelMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Job> selectAll() {
        return jobMapper.selectList(null);
    }

    @Override
    public Result getJobPersonNumbers() {
        //先把所以工作查出来
        List<Job> jobs = jobMapper.selectList(null);
        // 需要返回的数据
        List<JobPersonNumbers> jobPersonNumbers = new ArrayList<>();

        if (jobs != null) {
            for (Job job : jobs) {
                LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Employee::getJob, job.getCode());
                Integer count = employeeMapper.selectCount(wrapper);
                JobPersonNumbers jobNumbers = new JobPersonNumbers();
                jobNumbers.setJob(job.getDescription());
                jobNumbers.setNumber(count);
                jobPersonNumbers.add(jobNumbers);
            }
            return Result.success(jobPersonNumbers);
        } else {
            return Result.failure();
        }
    }

    @Override
    public Result welcome() {
        Integer jobNumber = jobMapper.selectCount(null);
        Integer employeeNumber = employeeMapper.selectCount(null);
        Integer personNumber = personnelMapper.selectCount(null);
        Integer departmentNumber = departmentMapper.selectCount(null);
        WelcomeDTO welcomeDTO = new WelcomeDTO();
        welcomeDTO.setDepartmentNumber(departmentNumber);
        welcomeDTO.setPersonnelNumber(personNumber);
        welcomeDTO.setEmployeeNumber(employeeNumber);
        welcomeDTO.setJobNumber(jobNumber);
        return Result.success(welcomeDTO);
    }
}

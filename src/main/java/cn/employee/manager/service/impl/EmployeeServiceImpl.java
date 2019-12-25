package cn.employee.manager.service.impl;

import cn.employee.manager.dto.EmployeeDTO;
import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.Employee;
import cn.employee.manager.entity.Job;
import cn.employee.manager.entity.Personnel;
import cn.employee.manager.mapper.EmployeeMapper;
import cn.employee.manager.mapper.JobMapper;
import cn.employee.manager.mapper.PersonnelMapper;
import cn.employee.manager.service.EmployeeService;
import cn.employee.manager.util.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zfitness
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PersonnelMapper personnelMapper;
    @Autowired
    private JobMapper jobMapper;

    /**
     * 分页查询所以员工
     * @param current
     * @param size
     * @param name
     * @return
     */
    @Override
    public Result list(Integer current, Integer size, String name) {
        Page<EmployeeDTO> page = new Page<>(current, size);
        return Result.success(employeeMapper.getEmployeeByPage(page, name));
    }

    /**
     * 删除员工
     * @param id
     * @return
     */
    @Override
    public Result deleteEmployeeById(Integer id) {
        int i = employeeMapper.deleteById(id);
        if (i == 0) {
            return Result.failure();
        } else {
            return Result.success();
        }
    }

    @Override
    public EmployeeDTO getUserById(Integer id) {
        return employeeMapper.getUserById(id);
    }

    /**
     * 增加员工
     * @param employee
     * @return
     */
    @Override
    public Map<String, Object> add(Employee employee) {
        Map<String,Object> resultMap = new HashMap<>(3);
        //先根据邮箱查询用户是否存在，存在则返回提示消息
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getEmail, employee.getEmail());
        List<Employee> employees = employeeMapper.selectList(wrapper);
        // fixes bug  #逻辑问题,  employees 不为空
        if (employees.size() != 0) {
            resultMap.put("code", 500001);
            resultMap.put("message", "邮箱已经存在");
            return resultMap;
        }
        //初始化默认密码
        String md5Password = MD5Util.getMD5("123", 11);
        employee.setPassword(md5Password);

        //设置用户状态
        employee.setState("T");
        int i = employeeMapper.insert(employee);

        //如果插入成功，人事表需要插入'新员工加入'的记录
        if (i == 1) {
            Personnel personnel = new Personnel();
            //0代表新员工加入
            personnel.setChangeId(0);
            personnel.setDescription("新来一位大佬");
            personnel.setPerson(employee.getId());
            personnel.setTime(new Date());
            personnelMapper.insert(personnel);
            resultMap.put("code", 200);
            resultMap.put("message", "插入成功");
            return resultMap;
        } else {
            resultMap.put("code", 500);
            resultMap.put("message", "插入失败了");
            return resultMap;
        }
    }

    /**
     * 把员工辞退
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> dismissEmployeeById(Integer id) {
        //先查询出员工
        Employee employee = employeeMapper.selectById(id);
        //改变员工状态
        employee.setState("F");
        employeeMapper.updateById(employee);
        //添加一条人事变动记录
        Personnel personnel = new Personnel();
        personnel.setChangeId(2);
        personnel.setPerson(employee.getId());
        personnel.setTime(new Date());
        personnel.setDescription("删库跑路了");
        personnelMapper.insert(personnel);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "辞退成功");
        return map;
    }

    /**
     * 更新用户
     * @param employee
     * @return
     */
    @Override
    public Map<String, Object> update(Employee employee) {

        Map<String, Object> map = new HashMap<>();
        //先从数据库查询原来员工的数据， 然后与更新后的数据进行对比
        Employee oldEmployee = employeeMapper.selectById(employee.getId());
        //如果密码发生变化，则进行密码加密
        if (!oldEmployee.getPassword().equals(employee.getPassword())) {
            employee.setPassword(MD5Util.getMD5(employee.getPassword(), 11));
        }
        //如果职务发生变化，则在人事表插入一条记录
        if ((employee.getJob() == null && oldEmployee.getJob() != null) || !employee.getJob().equals(oldEmployee.getJob())) {
            Personnel personnel = new Personnel();
            personnel.setPerson(employee.getId());
            personnel.setTime(new Date());
            personnel.setChangeId(1);
            personnel.setDescription(changeDescription(oldEmployee.getJob(), employee.getJob()));
            personnelMapper.insert(personnel);
        }
        int i = employeeMapper.updateById(employee);
        if (i == 1) {
            map.put("code", 200);
            map.put("message", "更新成功");
        } else {
            map.put("code", 500);
            map.put("message", "更新失败");
        }
        return map;
    }

    /**
     * 生成一个工作变动的描述字符串
     * @param jobId
     * @param newJobId
     * @return
     */
    public String changeDescription(Integer jobId, Integer newJobId) {
        StringBuilder sb = new StringBuilder();
        if (jobId == null) {
            sb.append("分配了职位:");
            Job job = jobMapper.selectById(newJobId);
            sb.append(job.getDescription());
        } else if (newJobId == null){
            sb.append("解除了职位:");
            Job job = jobMapper.selectById(jobId);
            sb.append(job.getDescription());
        } else {
            Job job = jobMapper.selectById(jobId);
            Job new_Job = jobMapper.selectById(newJobId);
            sb.append("从职位").append(job.getDescription()).append("跳到").append(new_Job.getDescription());
        }
        return sb.toString();
    }
}

package cn.employee.manager.controller;

import cn.employee.manager.dto.EmployeeDTO;
import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.EduLevel;
import cn.employee.manager.mapper.EduLevelMapper;
import cn.employee.manager.service.DepartmentService;
import cn.employee.manager.service.EmployeeService;
import cn.employee.manager.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zfitness
 */
@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private JobService jobService;
    @Autowired
    private EduLevelMapper eduLevelMapper;

    @GetMapping("/list")
    public Result list(@RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
                       @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return employeeService.list(current, size);
    }
    @GetMapping("/getUserById")
    public EmployeeDTO getUserById(@RequestParam(name = "id") Integer id) {
        return employeeService.getUserById(id);
    }
    @GetMapping("/delete")
    public Result deleteEmployeeById(@RequestParam(name = "id") Integer id) {
        return employeeService.deleteEmployeeById(id);
    }

    /**
     * 得到所以工作，部门，学历信息
     * @return
     */
    @GetMapping("/otherInfo")
    public Result getAllOtherInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("departments", departmentService.selectAll());
        info.put("jobs", jobService.selectAll());
        info.put("eduLevels", eduLevelMapper.selectList(null));
        return Result.success(info);
    }
}

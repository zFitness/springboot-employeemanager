package cn.employee.manager.controller;

import cn.employee.manager.dto.EmployeeDTO;
import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.Employee;
import cn.employee.manager.mapper.EduLevelMapper;
import cn.employee.manager.mapper.EmployeeMapper;
import cn.employee.manager.service.DepartmentService;
import cn.employee.manager.service.EmployeeService;
import cn.employee.manager.service.JobService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private JobService jobService;
    @Autowired
    private EduLevelMapper eduLevelMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    /**
     * 搜索接口
     */
    @GetMapping("/search")
    public Result search(@RequestParam(name = "name", required = false,defaultValue = "") String name,
                         @RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
                         @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return employeeService.list(current, size, name);
    }

    /**
     * 分页查询接口
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/list")
    public Result list(@RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
                       @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return employeeService.list(current, size, null);
    }

    /**
     * 根据id获取员工具体信息
     * @param id
     * @return
     */
    @GetMapping("/getUserById")
    public EmployeeDTO getUserAllInfoById(@RequestParam(name = "id") Integer id) {
        return employeeService.getUserById(id);
    }

    /**
     * 根据员工获取信息
     * @param id
     * @return
     */
    @GetMapping("/getEmployeeById")
    public Employee getUserById(@RequestParam(name = "id") Integer id) {
        return employeeMapper.selectById(id);
    }
    /**
     * 增加员工接口
     *
     * @param employee
     * @return
     */
    @PostMapping("/add")
    public Map<String, Object> addUser(@RequestBody Employee employee) {
        log.info(employee.toString());
        return employeeService.add(employee);
    }

    /**
     * 更新用户
     * @param employee
     * @return
     */
    @PostMapping("/update")
    public Map<String, Object> updateUser(@RequestBody Employee employee) {
        log.info(employee.toString());
        return employeeService.update(employee);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result deleteEmployeeById(@RequestParam(name = "id") Integer id) {
        return employeeService.deleteEmployeeById(id);
    }

    /**
     * 辞退员工
     *
     * @param id
     * @return
     */
    @GetMapping("/dismiss")
    public Map<String, Object> dismissEmployeeById(@RequestParam(name = "id") Integer id) {
        return employeeService.dismissEmployeeById(id);
    }

    /**
     * 得到所以工作，部门，学历信息
     *
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

    @GetMapping("/map")
    public Result getMap() {
        return employeeService.getMap();
    }
}

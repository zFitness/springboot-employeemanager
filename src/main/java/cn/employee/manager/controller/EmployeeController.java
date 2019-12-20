package cn.employee.manager.controller;

import cn.employee.manager.dto.result.Result;
import cn.employee.manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zfitness
 */
@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public Result list(@RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
                       @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return employeeService.list(current, size);
    }

    @GetMapping("/delete")
    public Result deleteEmployeeById(@RequestParam(name = "id") Integer id) {
        return employeeService.deleteEmployeeById(id);
    }
}

package cn.employee.manager.controller;

import cn.employee.manager.dto.result.Result;
import cn.employee.manager.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zfitness
 */
@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询所以部门
     * @return
     */
    @GetMapping("/list")
    public Result list() {
        return Result.success(departmentService.selectAll());
    }
}

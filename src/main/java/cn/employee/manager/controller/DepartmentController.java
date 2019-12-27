package cn.employee.manager.controller;

import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.Department;
import cn.employee.manager.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zfitness
 */
@Slf4j
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

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Map<String, Object> map) {
        log.info(map.toString());
        return departmentService.add(map);
    }
}

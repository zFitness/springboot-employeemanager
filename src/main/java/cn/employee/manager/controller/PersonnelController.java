package cn.employee.manager.controller;

import cn.employee.manager.dto.result.Result;
import cn.employee.manager.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 人事管理相关接口
 * @author zfitness
 */
@RestController
@CrossOrigin
@RequestMapping("/personnel")
public class PersonnelController {
    @Autowired
    private PersonnelService personnelService;

    @GetMapping("/list")
    public Result list(@RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
                       @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return personnelService.list(current, size);
    }


}

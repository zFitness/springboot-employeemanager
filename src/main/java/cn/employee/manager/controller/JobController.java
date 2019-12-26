package cn.employee.manager.controller;

import cn.employee.manager.dto.JobPersonNumbers;
import cn.employee.manager.dto.result.Result;
import cn.employee.manager.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
@CrossOrigin
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/numbers")
    public Result getJobPersonNumbers() {
        return jobService.getJobPersonNumbers();
    }

    /**
     * 返回欢迎页面数据
     * @return
     */
    @GetMapping("/welcome")
    public Result getWelcome() {
        return jobService.welcome();
    }
}

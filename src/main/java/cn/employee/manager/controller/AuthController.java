package cn.employee.manager.controller;

import cn.employee.manager.dto.LoginDTO;
import cn.employee.manager.dto.result.ResponseCode;
import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.Employee;
import cn.employee.manager.service.AuthService;
import cn.employee.manager.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户登录相关接口
 *
 * @author zfitness
 */
@RestController
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result login(@RequestParam(name = "email") String email,
                        @RequestParam(name = "password") String password) {
        //密码加密
        return authService.login(email, MD5Util.getMD5(password, 11));

    }
}

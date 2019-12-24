package cn.employee.manager.controller;

import cn.employee.manager.dto.LoginDTO;
import cn.employee.manager.dto.result.ResponseCode;
import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.Employee;
import cn.employee.manager.service.AuthService;
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
        List<Employee> user = authService.login(email, password);

        if (user == null || user.size() == 0) {
            return Result.failure(ResponseCode.USER_NOT_FOUND);
        } else  {
            // 生成token
            String token = UUID.randomUUID().toString();
            LoginDTO loginDTO = new LoginDTO();
            if (user.get(0).getAuthority() == 1) {
                loginDTO.setSuper(true);
            } else {
                loginDTO.setSuper(false);
            }
            loginDTO.setToken(token);
            loginDTO.setUserId(user.get(0).getId());
            return Result.success(loginDTO);
        }
    }
}

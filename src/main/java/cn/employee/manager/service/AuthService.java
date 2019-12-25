package cn.employee.manager.service;

import cn.employee.manager.dto.result.Result;
import cn.employee.manager.entity.Employee;

import java.util.List;

/**
 * @author zfitness
 */
public interface AuthService {
    Result login(String email, String password);
}

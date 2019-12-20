package cn.employee.manager.service;

import cn.employee.manager.entity.Employee;

import java.util.List;

/**
 * @author zfitness
 */
public interface AuthService {
    List<Employee> login(String email, String password);
}

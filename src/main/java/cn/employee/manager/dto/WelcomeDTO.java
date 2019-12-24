package cn.employee.manager.dto;

import lombok.Data;

/**
 * 封装welcome 需要显示的数据
 * @author zfitness
 */
@Data
public class WelcomeDTO {
    private Integer employeeNumber;
    private Integer jobNumber;
    private Integer personnelNumber;
    private Integer departmentNumber;
}

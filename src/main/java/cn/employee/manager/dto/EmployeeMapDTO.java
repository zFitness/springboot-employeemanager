package cn.employee.manager.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 封装员工分布信息
 * @author zfitness
 */
@Data
public class EmployeeMapDTO {
    private String address;
    private Integer number;
}

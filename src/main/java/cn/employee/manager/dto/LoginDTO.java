package cn.employee.manager.dto;

import lombok.Data;

/**
 * @author zfitness
 */
@Data
public class LoginDTO {
    private Integer userId;
    private boolean isSuper;
    private String token;
    private String avator_url;
}

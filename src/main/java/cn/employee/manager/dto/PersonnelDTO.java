package cn.employee.manager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 对外封装的人事信息封装
 * @author zfitness
 */
@Data
public class PersonnelDTO {
    private Integer id;
    private String username;
    private String userId;
    private String change;
    private String description;
    private String detail;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date time;

}

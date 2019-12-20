package cn.employee.manager.entity;

import lombok.Data;

/**
 * 人事变动记录表
 * @author zfitness
 */
@Data
public class Personnel {
    private Integer id;
    private Integer person;
    private Integer change;
    private String description;
}

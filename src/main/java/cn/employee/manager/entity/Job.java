package cn.employee.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 工作相关的表
 * @author zfitness
 */
@Data
public class Job {
    @TableId
    private Integer code;
    private String description;
}
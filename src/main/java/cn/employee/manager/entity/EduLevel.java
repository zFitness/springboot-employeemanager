package cn.employee.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zfitness
 */
@Data
@TableName("edu_level")
public class EduLevel {
    @TableId(type = IdType.AUTO)
    private Integer code;
    private String description;
}

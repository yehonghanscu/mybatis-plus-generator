package top.yehonghan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yehonghan
 * @since 2022-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "deptno", type = IdType.ASSIGN_ID)
    private Integer deptno;

    private String dname;

    private String loc;


}

package top.yehonghan.service.impl;

import top.yehonghan.entity.Emp;
import top.yehonghan.mapper.EmpMapper;
import top.yehonghan.service.EmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yehonghan
 * @since 2022-06-19
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

}

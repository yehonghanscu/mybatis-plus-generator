package top.yehonghan.service.impl;

import top.yehonghan.entity.Student;
import top.yehonghan.mapper.StudentMapper;
import top.yehonghan.service.StudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}

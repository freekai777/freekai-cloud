package freekaicloud.service.provider.service.impl;

import freekaicloud.service.provider.entity.User;
import freekaicloud.service.provider.mapper.UserMapper;
import freekaicloud.service.provider.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author freekai
 * @since 2020-09-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    UserMapper userMapper;

}

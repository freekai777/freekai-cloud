package freekaicloud.service.provider.controller;


import freekaicloud.service.provider.entity.User;
import freekaicloud.service.provider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author freekai
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/listAll")
    public List<User> listAll(){
        return userService.list();
    }
}

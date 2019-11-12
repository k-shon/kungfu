package top.annokshon.kungfu.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.annokshon.kungfu.entity.Role;
import top.annokshon.kungfu.entity.User;
import top.annokshon.kungfu.service.UserService;
import top.annokshon.kungfu.utils.JSONResult;
import top.annokshon.kungfu.utils.PasswordUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("user")
public class UserController {
    private Logger log = Logger.getLogger(UserController.class.getName());
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordUtils passwordUtils;
    //保存或更新用户信息
    @RequestMapping("save")
    public JSONResult save(User user){
        //加密密码
        user.setPassword(passwordUtils.encode(user.getPassword()));
        user.setRegisterTime(new Date());
        List<Role> roles = new ArrayList<Role>();
        Role role =  new Role();
        role.setId(1);
        roles.add(role);
        user.setRoles(roles);
        userService.save(user);
        return JSONResult.ok("保存用户信息成功");
    }
    //获取全部用户信息
    @RequestMapping("findAll")
    public JSONResult findAll(){
        List<User> users = userService.findAll();
        return JSONResult.ok(users);
    }
    //根据id获取用户信息
    @RequestMapping("{userId}")
    public JSONResult findById(@PathVariable("userId") int id){
        User user = userService.findById(id);
        return JSONResult.ok(user);
    }
    //根据id删除用户信息
    @RequestMapping("/delete/{userId}")
    public JSONResult deleteById(@PathVariable("userId") int id){
        userService.deleteById(id);
        return JSONResult.ok();
    }
}

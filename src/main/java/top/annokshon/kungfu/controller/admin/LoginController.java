package top.annokshon.kungfu.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.entity.User;
import top.annokshon.kungfu.mapper.UserMapper;
import top.annokshon.kungfu.service.PersonService;
import top.annokshon.kungfu.service.UserService;
import top.annokshon.kungfu.utils.JSONResult;

import java.util.Date;

/**
 * @author kshon
 * @description
 * @date 2019-08-26 22:00
 */
@RestController
public class LoginController {

    @Autowired
    private UserService  userService;
    @Autowired
    private PersonService personService;
    @RequestMapping("/login")
    public JSONResult login(@RequestBody User user){
        System.out.println("用户名："+user.getUsername());
        if(user.getUsername()!=null  && user.getPassword()!=null){
            user = userService.checkUser(user);
            if(user!=null) {
                Person person = personService.findByUser(user.getId());
                System.out.println("用户信息："+person);
                return JSONResult.ok(person);
            }
            else
                return JSONResult.errorMsg("用户名不存在或密码错误");
        }else{
            return JSONResult.errorMsg("用户名或密码不能为空");
        }
    }
    @RequestMapping("/register")
    public JSONResult register(@RequestBody User user){
        System.out.println("需要注册的用户："+user);
        if(user.getUsername()==null||user.getUsername()==""){
            return JSONResult.ok("用户名不能为空");
        }else if(user.getPassword()==null||user.getPassword()==""){
            return JSONResult.ok("密码不能为空");
        }
        userService.save(user);
        return JSONResult.ok();
    }
}

package top.annokshon.kungfu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.entity.Role;
import top.annokshon.kungfu.entity.User;
import top.annokshon.kungfu.mapper.PersonMapper;
import top.annokshon.kungfu.mapper.RoleMapper;
import top.annokshon.kungfu.mapper.UserMapper;
import top.annokshon.kungfu.utils.PasswordUtils;

import java.util.*;

/**
 * @author kshon
 * @date 2019-08-20 21:07
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private PasswordUtils passwordUtils;

    /**
     * 根据id查找用户
     */
    public User findById(int id){
        return userMapper.findById(id).get();
    }
    /**
     * 判断用户名密码是否正确
     */
    public User checkUser(User user){
        User newUser = userMapper.findByUsername(user.getUsername());
        if(newUser!=null){
            if(passwordUtils.checkPassword(user.getPassword(),newUser.getPassword())){  //验证密码
                return newUser;
            }
        }
        return null;
    }
    /**
     * 根据usernmae查找用户
     */
    public User findByUsername(String usernmae){
        return userMapper.findByUsername(usernmae);
    }
    /**
     * 查找所有用户并按时间排序
     */
    public List<User> findAll(){
        Sort sort = new Sort(Sort.Direction.DESC,"kf_register_time");
        return userMapper.findAll(sort);
    }
    /**
     * 保存或更新用户
     */
    @Transactional
    public void save(User user){
        //密码加密
        user.setPassword(passwordUtils.encode(user.getPassword()));
        user.setRegisterTime(new Date());
        user.setState(1);
        userMapper.save(user); //保存用户信息
        System.out.println(user);
        //设置权限信息
        Map<String,Object> map =  new HashMap<>();
        List<Role> roles = new ArrayList<Role>();
        roles.add(roleMapper.findById(3));
        roles.add(roleMapper.findById(2));
        roles.add(roleMapper.findById(1));
        map.put("roles",roles);
        map.put("user",user);
        roleMapper.saveToUserRole(map);
        //初始化个人信息
        Person person = new Person();
        person.setState("可用");
        person.setUser(user);
        personMapper.save(person);
    }
    /**
     * 删除用户
     */
    public void delete(User user){
        userMapper.delete(user);
    }
    /**
     * 删除用户
     */
    public void deleteById(int id){
        userMapper.deleteById(id);
    }
}

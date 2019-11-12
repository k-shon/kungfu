package top.annokshon.kungfu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.annokshon.kungfu.entity.User;
import top.annokshon.kungfu.mapper.UserMapper;

/**
 * @author kshon
 * @description  用于security安全实现的UserDetailsService接口
 * @date 2019-08-22 22:28
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(s);
        if (user==null){
            System.out.println("找不到用户名:"+s);
        }else{
            System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
            System.out.println("size:"+user.getRoles().size());
            System.out.println("role:"+user.getRoles().get(0).getName());
        }
        return user;
    }
}

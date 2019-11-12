package top.annokshon.kungfu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @author kshon
 * @description
 * @date 2019-09-27 22:50
 */
@Component
public class PasswordUtils {
    private Logger logger = Logger.getLogger(PasswordUtils.class.getName());
    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    public  String encode(String password){
        return bCryptPasswordEncoder.encode(password);
    }
    public  boolean checkPassword(String newPassword,String oldPassword){
        logger.info("校验密码...");
        return bCryptPasswordEncoder.matches(newPassword,oldPassword);  //(请求密码，数据库密码)
    }
}

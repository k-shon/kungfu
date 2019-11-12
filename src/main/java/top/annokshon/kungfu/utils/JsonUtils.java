package top.annokshon.kungfu.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.entity.Role;
import top.annokshon.kungfu.entity.User;
import top.annokshon.kungfu.entity.chat.ChatMessage;

import java.util.Date;

/**
 * @author kshon
 * @description
 * @date 2019-10-11 21:31
 */
@Component
public class JsonUtils {

    //添加自己的标签
    public String addMeTag(String cmStr){
        if(cmStr == null || cmStr == ""){
            return null;
        }
        JSONObject cmObject = JSON.parseObject(cmStr);
        cmObject.put("me",1);
        String newStr = cmObject.toString();
        return newStr;
    }

    //解析聊天记录
    public ChatMessage parseToChatMessage(String cmStr){
        if(cmStr == null || cmStr == ""){
            return null;
        }
        JSONObject cmObject = JSON.parseObject(cmStr);
        //解析内容
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSendTime(isContainsKeyToDate(cmObject,"sendTime"));
        chatMessage.setContext(isContainsKeyToString(cmObject,"context"));
        //解析person
        chatMessage.setFromPerson(parsePerson(isContainsKeyToString(cmObject,"fromPerson")));
        chatMessage.setToPerson(parsePerson(isContainsKeyToString(cmObject,"toPerson")));
        return  chatMessage;
    }
    //解析Person
    public Person parsePerson(String str){
        if(str == null || str == ""){
            return null;
        }
        JSONObject pObject = JSONObject.parseObject(str);
        Person person = new Person();
        person.setUser(parseUser(isContainsKeyToString(pObject,"user")));
        person.setId((Integer) isContainsKey(pObject,"id"));
        person.setNickname(isContainsKeyToString(pObject,"nickname"));
        person.setAvatar(isContainsKeyToString(pObject,"avatar"));
        person.setAge(isContainsKeyToString(pObject,"age"));
        person.setState(isContainsKeyToString(pObject,"state"));
        person.setSex(isContainsKeyToString(pObject,"sex"));
        person.setEmail(isContainsKeyToString(pObject,"email"));
        person.setMobileNumber(isContainsKeyToString(pObject,"mobileNumber"));
        person.setIdcard(isContainsKeyToString(pObject,"idcard"));
        person.setModifyTime(isContainsKeyToDate(pObject,"modifyTime"));
        return person;
    }
    //解析User
    public User parseUser(String str){
        if(str == null || str == ""){
            return null;
        }
        JSONObject uObject = JSONObject.parseObject(str);
        User user = new User();
        user.setId((Integer) isContainsKey(uObject,"id"));
        user.setUsername(isContainsKeyToString(uObject,"username"));
        user.setPassword(isContainsKeyToString(uObject,"password"));
        user.setState((Integer) isContainsKey(uObject,"state"));
        user.setRegisterTime(isContainsKeyToDate(uObject,"registerTime"));
        return user;
    }
    //解析role
    public Role parseRole(String str){
        if(str == null || str == ""){
            return null;
        }
        Role role = new Role();
        return role;
    }
    //判断是否存在某个key
    public Object isContainsKey(JSONObject  jsonObject,String key){
        if(jsonObject.containsKey(key)){
            return jsonObject.get(key);
        }else{
            return null;
        }
    }
    //判断是否存在某个key并返回String
    public String isContainsKeyToString(JSONObject  jsonObject,String key){
        if(jsonObject.containsKey(key)){
            return jsonObject.getString(key);
        }else{
            return null;
        }
    }
    //判断是否存在某个key并返回日期格式
    public Date isContainsKeyToDate(JSONObject  jsonObject,String key){
        if(jsonObject.containsKey(key)){
            return jsonObject.getDate(key);
        }else{
            return null;
        }
    }
}

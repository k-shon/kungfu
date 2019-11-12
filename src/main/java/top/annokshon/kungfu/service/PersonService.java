package top.annokshon.kungfu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.annokshon.kungfu.entity.DojoMember;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.entity.User;
import top.annokshon.kungfu.mapper.PersonMapper;
import top.annokshon.kungfu.utils.ObjectUtils;

import java.util.List;

/**
 * @author kshon
 * @description 个人信息服务类
 * @date 2019-08-20 23:58
 */
@Service
public class PersonService {
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private ObjectUtils objectUtils;
    public Person findByUser(int userId){
        return personMapper.findByUser(userId);
    }
    /**
     * 根据id查找用户
     */
    public Person findById(int id){
        return personMapper.findById(id);
    }
    /**
     * 查找所有用户并按id排序
     */
    public List<Person> findAll(){
        Sort sort = new Sort(Sort.Direction.DESC,"kf_id");
        return personMapper.findAll(sort);
    }
    /**
     * 保存或更新用户
     */
    public void saveOrUpdate(Person person) throws Exception {

        if(person.getNickname() == null) {
            throw new Exception("姓名不能为空");
        }else if(person.getId()==0){ //保存
            personMapper.save(person);
        }
        else{  //更新
            Person oldPerson = personMapper.findById(person.getId());
            person = (Person) objectUtils.copyByUnModify(person,oldPerson,new User());
            personMapper.update(person);
        }
    }
    /**
     * 删除用户
     */
    public void delete(Person person){
        personMapper.delete(person);
    }
    /**
     * 删除用户
     */
    public void deleteById(int id){
        personMapper.deleteById(id);
    }
}

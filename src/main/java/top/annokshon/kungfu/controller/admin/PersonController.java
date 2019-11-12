package top.annokshon.kungfu.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.entity.User;
import top.annokshon.kungfu.service.PersonService;
import top.annokshon.kungfu.utils.JSONResult;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("person")
public class PersonController {
    private Logger log = Logger.getLogger(PersonController.class.getName());
    @Autowired
    private PersonService personService;

    //
    @RequestMapping("/findByUser")
    public JSONResult findByUser(@RequestParam("id") int userId){
        System.out.println("查询个人信息："+userId);
        Person person = personService.findByUser(userId);
        System.out.println(person);
        return JSONResult.ok(person);
    }

    //保存或更新个人信息
    @RequestMapping("save")
    public JSONResult save(@RequestBody  Person person) throws Exception {
        personService.saveOrUpdate(person);
        return JSONResult.ok();
    }
    //获取全部个人信息
    @RequestMapping("findAll")
    public JSONResult findAll(){
        List<Person> persons = personService.findAll();
        return JSONResult.ok(persons);
    }
    //根据id获取个人信息
    @RequestMapping("/{personId}")
    public JSONResult findById(@PathVariable("personId") int id){
        Person person = personService.findById(id);
        return JSONResult.ok(person);
    }
    //根据id删除个人信息
    @RequestMapping("/delete/{personId}")
    public JSONResult deleteById(@PathVariable("personId") int id){
        personService.deleteById(id);
        return JSONResult.ok();
    }
}

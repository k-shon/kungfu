package top.annokshon.kungfu.controller.admin;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.annokshon.kungfu.entity.Dojo;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.mapper.DojoMapper;
import top.annokshon.kungfu.utils.JSONResult;

import java.util.Date;

@RestController
@RequestMapping("dojo")
public class DojoController {
    @Autowired
    private DojoMapper dojoMapper;

    @GetMapping("/getDojo")
    public JSONResult getDojo(@RequestParam("id") int id){
        return JSONResult.ok(dojoMapper.findById(id));
    }
    @GetMapping("/save")
    public void save(){
        Dojo dojo  = new Dojo();
        dojo.setName("太极拳");
        dojo.setPerson(new Person("kshon","1654313216545"));
        dojoMapper.save(dojo);
    }
    //武馆入驻
    @PostMapping("register")
    public JSONResult register(@RequestBody Dojo dojo){
        dojo.setStatus(0);
        dojo.setCreatetime(new Date());
        dojoMapper.save(dojo);
        return JSONResult.ok();
    }
}

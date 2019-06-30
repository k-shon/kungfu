package top.annokshon.kungfu.controller.admin;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.annokshon.kungfu.entity.Dojo;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.entity.Picture;
import top.annokshon.kungfu.mapper.DojoMapper;
import top.annokshon.kungfu.service.DojoService;
import top.annokshon.kungfu.utils.JSONResult;

import java.util.Date;

@RestController
@RequestMapping("dojo")
public class DojoController {
    @Autowired
    private DojoService dojoService;

    @GetMapping("/getDojo")
    public JSONResult getDojo(@RequestParam("id") int id){
        return JSONResult.ok(dojoService.findById(id));
    }
    @GetMapping("/save")
    public void save(){
        Dojo dojo  = new Dojo();
        dojo.setName("太极拳");
        dojo.setPerson(new Person("kshon","1654313216545"));
        dojoService.save(dojo);
    }
    //武馆入驻
    @PostMapping("/register")
    public JSONResult register(Dojo dojo, @RequestParam("file")MultipartFile file){
        //设置武馆状态为冻结
        dojo.setStatus(0);
        dojo.setCreatetime(new Date());
        //上传头像
        Picture picture = dojoService.uploadSingleImage(dojo,file);
        dojo.setPicture(picture);
        //保存武馆信息
        dojoService.save(dojo);
        return JSONResult.ok(dojo);
    }
}

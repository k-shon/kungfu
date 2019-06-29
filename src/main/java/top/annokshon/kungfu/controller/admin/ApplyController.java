package top.annokshon.kungfu.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.annokshon.kungfu.mapper.ApplyMapper;
import top.annokshon.kungfu.utils.JSONResult;

/*
报名或合作控制器
 */
@RestController
@RequestMapping("apply")
public class ApplyController {
    @Autowired
    private ApplyMapper applyMapper;

    @RequestMapping("dojo")
    public JSONResult dojo(){
        return JSONResult.ok("dojo");
    }
}

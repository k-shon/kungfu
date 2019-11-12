package top.annokshon.kungfu.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.annokshon.kungfu.entity.DojoApply;
import top.annokshon.kungfu.mapper.DojoApplyMapper;
import top.annokshon.kungfu.service.DojoApplyService;
import top.annokshon.kungfu.utils.JSONResult;

import java.util.Date;
import java.util.logging.Logger;

/*
报名控制器
 */
@RestController
@RequestMapping("apply")
public class DojoApplyController {
    private Logger log = Logger.getLogger(DojoApplyController.class.getName());
    @Autowired
    private DojoApplyService dojoApplyService;

    @RequestMapping("/applyDojo")
    public JSONResult applyDojo(DojoApply dojoApply){
        log.info("报名武馆...");
        dojoApply.setApplyState(0); //正在报名状态
        dojoApply.setApplyTime(new Date());
        dojoApplyService.saveOrUpdate(dojoApply);
        return JSONResult.ok();
    }
}

package top.annokshon.kungfu.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.annokshon.kungfu.entity.DojoMember;
import top.annokshon.kungfu.service.DojoMemberService;
import top.annokshon.kungfu.utils.JSONResult;

@RestController
@RequestMapping("member")
public class DojoMemberController {
    private Log log = LogFactory.getLog(DojoMemberController.class);
    @Autowired
    private DojoMemberService dojoMemberService;

    /*查询全部成员*/
    @RequestMapping("/findAll")
    public JSONResult findAll(){
        return JSONResult.ok(dojoMemberService.findAll());
    }
    /*更新成员*/
    @RequestMapping("/update")
    public JSONResult update(@RequestBody DojoMember dojoMember){
        log.info("更新成员："+dojoMember.getId());
        try {
            dojoMemberService.saveOrUpdate(dojoMember);
        } catch (Exception e) {
            return JSONResult.errorMsg(e.getMessage());
        }
        return JSONResult.ok();
    }
    /*删除成员*/
    @RequestMapping("/delete")
    public JSONResult delete(@RequestBody DojoMember dojoMember){
        log.info("删除成员："+dojoMember);
        try{
            dojoMemberService.delete(dojoMember);
        }catch (Exception e){
            return JSONResult.errorMsg(e.getMessage());
        }
        return JSONResult.ok();
    }
}

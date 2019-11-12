package top.annokshon.kungfu.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.annokshon.kungfu.entity.Dojo;
import top.annokshon.kungfu.service.DojoService;
import top.annokshon.kungfu.utils.JSONResult;

import java.util.List;


@RestController
@RequestMapping("dojo")
public class DojoController {
    private Log log = LogFactory.getLog(DojoController.class);
    @Autowired
    private DojoService dojoService;

    //武馆入驻
    @RequestMapping("/settle")
    public JSONResult settleDojo(Dojo dojo){
        log.info("武馆入驻："+dojo.toString());
        try {
            dojo.setState(1); //入驻状态
            dojoService.save(dojo);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg(e.getMessage());
        }
        return JSONResult.ok();
    }
    //查询全部武馆
    @RequestMapping("/findAll")
    public JSONResult findAll(){
        log.info("查询所有武馆信息。。");
        return JSONResult.ok(dojoService.findAll());
    }
    //根据id获取武馆
    @RequestMapping("/{dojoId}")
    public JSONResult findById(@PathVariable("dojoId") int id){
        Dojo dojo = dojoService.findById(id);
        return JSONResult.ok(dojo);
    }

    //根据价格降序排序
    @RequestMapping("/sortByPrice")
    public JSONResult findByPriceSortDesc(@RequestParam("sort")int sort){
        return JSONResult.ok(dojoService.findByPriceSort(sort));
    }

    //根据距离由近到远排序
    @RequestMapping("/sortByDistanceASC")
    public JSONResult sortByDistanceASC(@RequestParam("latitude")String latitude,@RequestParam("longitude")String longitude){
        List<Dojo> dojos = dojoService.sortByDistenceASC(latitude,longitude);
        for(Dojo d:dojos){
            System.out.println("武馆【"+d.getDojoName()+"】的geoCode为【"+d.getGeoCode()+"】");
        }
        return JSONResult.ok(dojos);
    }
    //更新武馆
    @RequestMapping("/update")
    public JSONResult update(@RequestBody Dojo dojo){
        log.info("更新武馆："+dojo.toString());
        try {
            dojoService.updateDojo(dojo);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg(e.getMessage());
        }
        return JSONResult.ok();
    }
}

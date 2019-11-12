package top.annokshon.kungfu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.locationtech.spatial4j.io.GeohashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import top.annokshon.kungfu.entity.Dojo;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.entity.Picture;
import top.annokshon.kungfu.mapper.DojoMapper;
import top.annokshon.kungfu.mapper.PictureMapper;
import top.annokshon.kungfu.utils.JSONResult;
import top.annokshon.kungfu.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DojoService {
    @Autowired
    private DojoMapper dojoMapper;
    @Autowired
    private ObjectUtils objectUtils;
    private Log log = LogFactory.getLog("ImageUploadController");
    private final int precision = 4;
    //保存武馆信息
    public void save(Dojo dojo) throws Exception {
        if(dojo.getId()==0 || dojo.getDojoName() == null){
            throw new Exception("武馆信息为空！");
        }
        //根据经纬度计算geohsdh编码,精度为4(39.1km范围内)
        if(dojo.getDojoLatitude()!=null && dojo.getDojoLongitude()!=null){
            String geo_code = GeohashUtils.encodeLatLon(Double.parseDouble(dojo.getDojoLatitude()),Double.parseDouble(dojo.getDojoLongitude()),precision);
            dojo.setGeoCode(geo_code);
        }
        dojo.setDojoRegisterTime(new Date());
        dojoMapper.save(dojo);
        log.info("保存武馆【"+dojo.getDojoName()+"】成功");
    }
    //根据id查询武馆信息
    public Dojo findById(Integer id){return dojoMapper.findById(id).get();}
    //查询所有武馆并按创建时间排序
    public List<Dojo> findAll(){
        return dojoMapper.findAll();
    }
    //根据价格排序
    public List<Dojo> findByPriceSort(int sort){
        Sort sort1 = null;
        List<String> properties = new ArrayList<String>();
        properties.add("dojoApplyPrice");
        if(sort==0){  //0代表升序
            sort1 = new Sort(Sort.Direction.ASC,properties);
        }else if (sort==1){  //1代表降序
            sort1 = new Sort(Sort.Direction.DESC,properties);
        }
        System.out.println(dojoMapper.findAll().toArray());
        return dojoMapper.findAll(sort1);
    }

    //距离由近到远排序
    public List<Dojo> sortByDistenceASC(String latitude,String longitude){
        //根据经纬度计算geohsdh编码,精度为4(39.1km范围内)
        String geo_code = GeohashUtils.encodeLatLon(Double.parseDouble(latitude),Double.parseDouble(longitude),precision);
        log.info("匹配的geoCode【"+geo_code+"】");
        return dojoMapper.findByGeoCode(geo_code);
    }

    //更新武馆信息
    public void updateDojo(Dojo dojo)throws Exception{
        if(dojo.getId()==0||dojo.getPerson().getId()==0){
            throw new Exception("武馆id为空");
        }else{
            Dojo oldDojo = dojoMapper.findById(dojo.getId()).get();
            dojo = (Dojo) objectUtils.copyByUnModify(dojo,oldDojo,new Person());
        }
        dojoMapper.save(dojo);
    }
}

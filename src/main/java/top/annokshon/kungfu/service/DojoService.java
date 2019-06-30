package top.annokshon.kungfu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import top.annokshon.kungfu.entity.Dojo;
import top.annokshon.kungfu.entity.Picture;
import top.annokshon.kungfu.mapper.DojoMapper;
import top.annokshon.kungfu.mapper.PictureMapper;
import top.annokshon.kungfu.utils.JSONResult;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class DojoService {
    @Autowired
    private DojoMapper dojoMapper;
    @Autowired
    private PictureMapper pictureMapper;
    //获取项目classes/static的地址
    String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
    private  final String URL = "http://localhost:1111/";
    private Log log = LogFactory.getLog("ImageUploadController");
    /*
    上传单个图片:返回Picture对象
     */
    public Picture uploadSingleImage(Dojo dojo, MultipartFile file){
        String fileName = file.getOriginalFilename();  //获取文件名
        //图片访问URI(即除了协议、地址和端口号的URL)
        String url_path = "image"+ File.separator+fileName;
        log.info("图片访问uri："+url_path);
        String savePath = path+File.separator+url_path;  //图片保存路径
        log.info("图片保存地址："+savePath);
        File saveFile = new File(savePath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            file.transferTo(saveFile);  //将临时存储的文件移动到真实存储路径下
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将图片信息存储到数据库
        Picture picture = new Picture(file.getName(),URL+url_path,savePath,new Date());
        pictureMapper.save(picture);
        //返回图片访问地址
        log.info("访问URL："+URL+url_path);
        return picture;
    }
    //保存武馆信息
    public void save(Dojo dojo){dojoMapper.save(dojo);}
    //根据id查询武馆信息
    public Dojo findById(Integer id){return dojoMapper.findById(id).get();}
}

package top.annokshon.kungfu.controller.upload;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.annokshon.kungfu.entity.Picture;
import top.annokshon.kungfu.mapper.PictureMapper;
import top.annokshon.kungfu.utils.JSONResult;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("imgUpload")
public class ImageUploadController {

    private  final String URL = "http://localhost/";
    private Log log = LogFactory.getLog(ImageUploadController.class);

    @PostMapping("/single")
    public JSONResult singleImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws FileNotFoundException {  //参数名需与前端文件标签名一样
        //获取项目classes/static的地址
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String fileName = file.getOriginalFilename();  //获取文件名
        //图片访问URI(即除了协议、地址和端口号的URL)
        String url_path = "image"+File.separator+fileName;
        String savePath = path+File.separator+url_path;  //图片保存路径
        File saveFile = new File(savePath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            file.transferTo(saveFile);  //将临时存储的文件移动到真实存储路径下
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回图片访问地址
        log.info("访问URL："+URL+url_path);
        return JSONResult.ok(URL+url_path);
    }
}

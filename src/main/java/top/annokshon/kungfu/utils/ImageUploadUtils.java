package top.annokshon.kungfu.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kshon
 * @description  图片上传处理工具
 * @date 2019-08-27 23:34
 */
public class ImageUploadUtils {

    @Value("${url}")
    private  String URL;
    private Log log = LogFactory.getLog(ImageUploadUtils.class);

}

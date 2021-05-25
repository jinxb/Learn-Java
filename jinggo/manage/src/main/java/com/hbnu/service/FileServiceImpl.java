package com.hbnu.service;

import com.hbnu.vo.ImageVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService {
    //图片存储真实路径分根目录
    @Value("${image.localDirPath}")
    private String localDirPath;
    //图片的虚拟路径
    @Value("${image.urlDirPath}")
    private String urlDirPath;
    /**
     * 思路：
     * 1.获取上传文件的名字
     * 1.1校验文件格式,jpg,jpeg,png,gif
     * 1.2校验恶意程序/文件,木马,exe,jpg,视频.mp4.jpg
     * 2.创建文件目录，用于存放上传的文件 yyyy/MM/dd
     * 3.创建文件,用于存储用户上传的文件 uuid(唯一字符串)
     * 4.上传文件
     * 5.回显图片
     *
     * @param uploadFile
     * @return
     */
    @Override
    public ImageVo upload(MultipartFile uploadFile) {


        //1.1.获取上传文件的名字
        String fileName = uploadFile.getOriginalFilename();
        fileName = fileName.toLowerCase();
        //校验文件格式,jpg,jpeg,png,gif
        if (!fileName.matches("^.+\\.(jpg|jpeg|png|gif)$")) {
            return ImageVo.fail();
        }

        //1.2校验恶意程序/文件,木马,exe,jpg,视频.mp4.jpg
        try {
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if (width == 0 || height == 0) {
                return ImageVo.fail();
            }

            //创建文件目录,用于存放上传的文件
            String dateDir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String realFileDir = localDirPath + dateDir + "/";
            File realFile = new File(realFileDir);
            if (!realFile.exists()) {
                realFile.mkdirs();
            }

            //3.创建文件,用于存储用户上传的文件 uuid(唯一字符串)
            String realFileName = UUID.randomUUID().toString();
            //从哪个地方开始截取
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            String realFileNamePath = realFileDir + realFileName + fileType;

            //4.上传文件
            uploadFile.transferTo(new File(realFileNamePath));
            //5.图片回显 url虚拟路径,http://域名/图片地址
            String url = urlDirPath + dateDir + "/" + realFileName + fileType;
            return new ImageVo(0,url,width,height);
        } catch (IOException e) {
            e.printStackTrace();
            return ImageVo.fail();
        }
    }

}

package com.hbnu.controller;

import com.hbnu.service.FileService;
import com.hbnu.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    @RequestMapping("/file")
    public String fileUpload(MultipartFile fileImage) throws IOException {
        //1、获取图片名字
        String fileName = fileImage.getOriginalFilename();

        //2、指定图片上传的位置
        String dir = "C:/Users/PC/Pictures/Camera Roll/jinggo";

        File dirPath = new File(dir);
        if (!dirPath.isDirectory()) {
            dirPath.mkdirs();
        }

        //3、指定上传文件的名称
        String realFileName = "C:/Users/PC/Pictures/Camera Roll/jinggo" + fileName;
        File dirFileName = new File(realFileName);

        //4、上传文件
        fileImage.transferTo(dirFileName);

        return "文件上传成功！！！";
    }

    @Autowired
    private FileService fileService;

    /**
     * url地址:http://localhost:8090/pic/upload?dir=image
     */
    @RequestMapping("/pic/upload")
    public ImageVO uploadFile(MultipartFile uploadFile) {
        return fileService.upload(uploadFile);
    }
}

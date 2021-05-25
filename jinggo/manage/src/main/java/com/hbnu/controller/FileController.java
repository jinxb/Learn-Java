package com.hbnu.controller;

import com.hbnu.service.FileService;
import com.hbnu.vo.ImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {
//    @RequestMapping("/pic/upload")
    public String fileUpload(MultipartFile files) throws IOException {
        // 1.获取图片名称
        String fileName = files.getOriginalFilename();

        // 2.指定图片上传目录
        String dir = "C:\\Users\\jinbin\\Desktop\\jingo-image";
        File dirPath = new File(dir);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }

        // 3.指定上传文件的名字
        String realFileName = "C:\\Users\\jinbin\\Desktop\\jingo-image/" + fileName;
        File dirFileName = new File(realFileName);

        // 4.上传文件
        files.transferTo(dirFileName);

        return "文件上传成功！";


    }

    @Autowired
    private FileService fileService;
    /**
     * url地址：
     */
    @RequestMapping("/pic/upload")
    public ImageVo uploadFile(MultipartFile uploadFile){
        return fileService.upload(uploadFile);

    }
}

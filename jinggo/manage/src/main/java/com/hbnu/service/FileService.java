package com.hbnu.service;

import com.hbnu.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ImageVO upload(MultipartFile uploadFile);
}

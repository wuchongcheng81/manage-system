package com.manage.system.controller;

import com.manage.system.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wucc
 * @date 2019/8/29 8:40
 */
@RestController
@RequestMapping(value = "/upload")
public class UploadController {

    @Value("${my.domain}")
    String host;
    @Value("${upload.file.dir}")
    String filePath;
    @Value("${upload.pattern.img}")
    String imgPattern;

    @Autowired
    private PhotoService photoService;

    @PostMapping(value = "/uploadImg")
    public String uploadImg(MultipartFile file, String relatedId, Integer isShow) {
        String imgUrl = photoService.save(file, filePath, imgPattern, relatedId, host, isShow != null ? isShow : 0);
        return imgUrl;
    }
}

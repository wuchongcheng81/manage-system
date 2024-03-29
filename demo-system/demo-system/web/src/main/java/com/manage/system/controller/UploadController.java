package com.manage.system.controller;

import com.manage.system.bean.Photo;
import com.manage.system.dto.WangEditorDTO;
import com.manage.system.response.ResultData;
import com.manage.system.service.PhotoService;
import com.manage.system.service.UploadServiceFeign;
import com.manage.system.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private UploadServiceFeign uploadServiceFeign;

    @PostMapping(value = "/image")
    public ResultData uploadImg(MultipartFile file) {
        String imgUrl = FileUtil.uploadFileReturnFileUrl(host, imgPattern, file, filePath);
        return new ResultData(true, imgUrl);
    }

    @RequestMapping(value = "/wangEditorUpload")
    public WangEditorDTO wangEditorUpload(MultipartFile file) {
        String imgUrl = FileUtil.uploadFileReturnFileUrl(host, imgPattern, file, filePath);
        String[] url = {imgUrl};
        WangEditorDTO wangEditor = new WangEditorDTO(url);
        return wangEditor;
    }
}

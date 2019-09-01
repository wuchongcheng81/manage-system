package com.manage.system.service;

import com.manage.system.bean.Photo;
import com.manage.system.response.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(name = "upload-service", url = "${my.domain}")
public interface UploadServiceFeign {

    @RequestMapping(value = "/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultData uploadImg(@RequestPart("file") MultipartFile file, @RequestParam("relatedId") String relatedId, @RequestParam("isShow") Integer isShow);
}

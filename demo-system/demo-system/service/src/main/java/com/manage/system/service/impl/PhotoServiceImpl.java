package com.manage.system.service.impl;

import com.manage.system.base.AbstractService;
import com.manage.system.bean.Photo;
import com.manage.system.dao.PhotoMapper;
import com.manage.system.service.PhotoService;
import com.manage.system.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author wucc
 * @date 2019/8/29 8:28
 */
@Slf4j
@Component
@Transactional
public class PhotoServiceImpl extends AbstractService<Photo, PhotoMapper> implements PhotoService {

    @Override
    public String save(MultipartFile file, String filePath, String pattern, String relatedId, String host, int isShow) {

        String imgUrl = FileUtil.uploadFileReturnFileName(file, filePath);
        if(StringUtils.isNotBlank(imgUrl)) {
            Photo photo = new Photo();
            photo.setImgUrl(host + pattern + "/" + imgUrl);
            photo.setIsShow(isShow);
            photo.setRelatedId(relatedId);
            photo.setCreateTime(new Date());
            mapper.insert(photo);
            return imgUrl;
        }
        return null;
    }
}

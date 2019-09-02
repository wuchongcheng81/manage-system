package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
public class PhotoServiceImpl extends AbstractService<Photo, Integer, PhotoMapper> implements PhotoService {

    @Override
    public Photo save(MultipartFile file, String filePath, String pattern, String relatedId, String host, int isShow) {

        String imgUrl = FileUtil.uploadFileReturnFileName(file, filePath);
        if (StringUtils.isNotBlank(imgUrl)) {
            Photo photo = new Photo();
            photo.setId(uuid());
            photo.setImgUrl(host + pattern + "/" + imgUrl);
            photo.setIsShow(isShow);
            photo.setRelatedId(relatedId);
            photo.setCreateTime(new Date());
            mapper.insert(photo);
            return photo;
        }
        return null;
    }

    @Override
    public Photo update(String id, MultipartFile file, String filePath, String pattern, String relatedId, String host, Integer isShow) {
        Photo photo = new Photo();
        if (!file.isEmpty()) {
            String imgUrl = FileUtil.uploadFileReturnFileName(file, filePath);
            photo.setImgUrl(host + pattern + "/" + imgUrl);
        }
        photo.setIsShow(isShow != null ? isShow : null);
        photo.setRelatedId(StringUtils.isNotBlank(relatedId) ? relatedId : null);
        mapper.update(photo, getWrapper(new Photo(id)));
        return mapper.selectById(id);
    }

    @Override
    public int updateIsShowById(String id, Integer isShow) {
        Photo photo = new Photo();
        photo.setIsShow(isShow);
        return mapper.update(photo, getWrapper(new Photo(id)));
    }

    @Override
    public Photo findById(String id) {
        return mapper.selectById(id);
    }

    @Override
    public IPage<Photo> findPage(Photo entity) {
        return null;
    }

    private QueryWrapper getWrapper(Photo entity) {
        QueryWrapper<Photo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", entity.getId());
        return wrapper;
    }
}

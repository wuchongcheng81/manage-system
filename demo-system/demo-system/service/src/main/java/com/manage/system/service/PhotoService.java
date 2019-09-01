package com.manage.system.service;

import com.manage.system.bean.Photo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wucc
 * @date 2019/8/29 8:27
 */
public interface PhotoService {

    Photo save(MultipartFile file, String filePath, String pattern, String relatedId, String host, int isShow);

    Photo update(String id, MultipartFile file, String filePath, String pattern, String relatedId, String host, Integer isShow);

    int updateIsShowById(String id, Integer isShow);

    Photo findById(String id);
}

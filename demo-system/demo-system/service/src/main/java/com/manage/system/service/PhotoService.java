package com.manage.system.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author wucc
 * @date 2019/8/29 8:27
 */
public interface PhotoService {

    String save(MultipartFile file, String filePath, String pattern, String relatedId, String host, int isShow);
}

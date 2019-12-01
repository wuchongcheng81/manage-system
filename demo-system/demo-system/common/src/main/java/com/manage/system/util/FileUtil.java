package com.manage.system.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author wucc
 * @date 2019/8/29 8:30
 */
public class FileUtil {
    public static List<String> getAllFileTypes() {
        List<String> fileTypes = new ArrayList<>();
        fileTypes.add("jpg");
        fileTypes.add("png");
        fileTypes.add("jpeg");
        fileTypes.add("gif");
        fileTypes.add("doc");
        fileTypes.add("docx");
        fileTypes.add("xls");
        fileTypes.add("xlsx");
        fileTypes.add("doc");
        return fileTypes;
    }

    public static List<String> getImagesFileTypes() {
        List<String> fileTypes = new ArrayList<>();
        fileTypes.add("jpg");
        fileTypes.add("png");
        fileTypes.add("jpeg");
        fileTypes.add("gif");
        return fileTypes;
    }

    public static String uploadFile(MultipartFile file, String uploadDir) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID() + suffixName;

            String filePath = uploadDir + getCurrentDate() + "/" + fileName;
            upload(file, filePath);
            return filePath;
        }
        return null;
    }

    public static String uploadFileReturnFileName(MultipartFile file, String uploadDir) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID() + suffixName;
            String dirName = getCurrentDate() + "/" + fileName;;
            String filePath = uploadDir + dirName;
            upload(file, filePath);
            return dirName;
        }
        return null;
    }

    public static String uploadFileReturnFileUrl(String host, String pattern, MultipartFile file, String uploadDir) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID() + suffixName;
            String dirName = getCurrentDate() + "/" + fileName;
            String filePath = uploadDir + dirName;
            upload(file, filePath);
            return host + pattern + "/" + dirName;
        }
        return null;
    }

    public static String uploadKindEditorFile(String host, String pattern, MultipartFile file, String uploadDir, String kindEditorDir) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID() + suffixName;
            String dirName = getCurrentDate() + "/" + fileName;;
            String filePath = uploadDir.concat(kindEditorDir) + dirName;
            upload(file, filePath);
            return host + pattern + "/" + kindEditorDir + "/" + dirName;
        }
        return null;
    }

    public static void upload(MultipartFile file, String filePath) {
        File dest = new File(filePath);
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }
}

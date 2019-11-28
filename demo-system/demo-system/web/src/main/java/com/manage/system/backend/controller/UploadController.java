package com.manage.system.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.manage.system.backend.dto.WangEditorDTO;
import com.manage.system.comparator.NameComparator;
import com.manage.system.comparator.SizeComparator;
import com.manage.system.comparator.TypeComparator;
import com.manage.system.response.ResultData;
import com.manage.system.service.PhotoService;
import com.manage.system.service.UploadServiceFeign;
import com.manage.system.util.FileUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import static rx.internal.operators.NotificationLite.getError;

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

    /**
     * KindEditor
     *
     * @param response
     * @throws FileUploadException
     * @throws IOException
     */
    @RequestMapping(value = "/uploadJson")
    public void uploadJson(HttpServletResponse response, MultipartFile imgFile) throws FileUploadException, IOException {
        PrintWriter out = response.getWriter();

        String kindEditorDir = "kindEditor/image/";

        String imgUrl = FileUtil.uploadKindEditorFile(host, imgPattern, imgFile, filePath, kindEditorDir);

        JSONObject obj = new JSONObject();
        obj.put("error", 0);
        obj.put("url", imgUrl);
        out.println(obj.toJSONString());


//        //文件保存目录路径
//        String savePath = filePath + "/kindEditor/";
//
//        //文件保存目录URL
//        String saveUrl = filePath + "/kindEditor";
//
//        //定义允许上传的文件扩展名
//        HashMap<String, String> extMap = new HashMap<>();
//        extMap.put("image", "gif,jpg,jpeg,png,bmp");
////        extMap.put("flash", "swf,flv");
////        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
////        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
//
//        //最大文件大小
//        long maxSize = 1000000;
//
//        response.setContentType("text/html; charset=UTF-8");
//
//        if (!ServletFileUpload.isMultipartContent(request)) {
//            out.println(getError("请选择文件。"));
//            return;
//        }
//        //检查目录
//        File uploadDir = new File(savePath);
//        if (!uploadDir.isDirectory()) {
//            out.println(getError("上传目录不存在。"));
//            return;
//        }
//        //检查目录写权限
//        if (!uploadDir.canWrite()) {
//            out.println(getError("上传目录没有写权限。"));
//            return;
//        }
//
//        String dirName = request.getParameter("dir");
//        if (dirName == null) {
//            dirName = "image";
//        }
//        if (!extMap.containsKey(dirName)) {
//            out.println(getError("目录名不正确。"));
//            return;
//        }
//        //创建文件夹
//        savePath += dirName + "/";
//        saveUrl += dirName + "/";
//        File saveDirFile = new File(savePath);
//        if (!saveDirFile.exists()) {
//            saveDirFile.mkdirs();
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String ymd = sdf.format(new Date());
//        savePath += ymd + "/";
//        saveUrl += ymd + "/";
//        File dirFile = new File(savePath);
//        if (!dirFile.exists()) {
//            dirFile.mkdirs();
//        }
//
//        FileItemFactory factory = new DiskFileItemFactory();
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        upload.setHeaderEncoding("UTF-8");
//        List items = upload.parseRequest(request);
//        Iterator itr = items.iterator();
//        while (itr.hasNext()) {
//            FileItem item = (FileItem) itr.next();
//            String fileName = item.getName();
//            long fileSize = item.getSize();
//            if (!item.isFormField()) {
//                //检查文件大小
//                if (item.getSize() > maxSize) {
//                    out.println(getError("上传文件大小超过限制。"));
//                    return;
//                }
//                //检查扩展名
//                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//                if (!Arrays.asList(extMap.get(dirName).split(",")).contains(fileExt)) {
//                    out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
//                    return;
//                }
//
//                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//                String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
//                try {
//                    File uploadedFile = new File(savePath, newFileName);
//                    item.write(uploadedFile);
//                } catch (Exception e) {
//                    out.println(getError("上传文件失败。"));
//                    return;
//                }
//
//                JSONObject obj = new JSONObject();
//                obj.put("error", 0);
//                obj.put("url", saveUrl + newFileName);
//                out.println(obj.toJSONString());
//            }
//        }
    }

    @RequestMapping(value = "/fileManagerJson")
    public void fileManagerJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        String rootPath = filePath + "/kindEditor/";
        String rootUrl = request.getContextPath() + imgPattern + "/kindEditor/";
        //图片扩展名
        String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};

        String dirName = request.getParameter("dir");
        if (dirName != null) {
            if (!Arrays.asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)) {
                out.println("Invalid Directory name.");
                return;
            }
            rootPath += dirName + "/";
            rootUrl += dirName + "/";
            File saveDirFile = new File(rootPath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
        }
        //根据path参数，设置各路径和URL
        String path = request.getParameter("path") != null ? request.getParameter("path") : "";
        String currentPath = rootPath + path;
        String currentUrl = rootUrl + path;
        String currentDirPath = path;
        String moveupDirPath = "";
        if (!"".equals(path)) {
            String str = currentDirPath.substring(0, currentDirPath.length() - 1);
            moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }

        //排序形式，name or size or type
        String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

        //不允许使用..移动到上一级目录
        if (path.indexOf("..") >= 0) {
            out.println("Access is not allowed.");
            return;
        }
        //最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            out.println("Parameter is not valid.");
            return;
        }
        //目录不存在或不是目录
        File currentPathFile = new File(currentPath);
        if (!currentPathFile.isDirectory()) {
            out.println("Directory does not exist.");
            return;
        }

        //遍历目录取的文件信息
        List<Hashtable> fileList = new ArrayList<>();
        if (currentPathFile.listFiles() != null) {
            for (File file : currentPathFile.listFiles()) {
                Hashtable<String, Object> hash = new Hashtable<>();
                String fileName = file.getName();
                if (file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if (file.isFile()) {
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", Arrays.asList(fileTypes).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                fileList.add(hash);
            }
        }

        if ("size".equals(order)) {
            Collections.sort(fileList, new SizeComparator());
        } else if ("type".equals(order)) {
            Collections.sort(fileList, new TypeComparator());
        } else {
            Collections.sort(fileList, new NameComparator());
        }
        JSONObject result = new JSONObject();
        result.put("moveup_dir_path", moveupDirPath);
        result.put("current_dir_path", currentDirPath);
        result.put("current_url", currentUrl);
        result.put("total_count", fileList.size());
        result.put("file_list", fileList);

        response.setContentType("application/json; charset=UTF-8");
        out.println(result.toJSONString());
    }

    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }
}

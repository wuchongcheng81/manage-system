package com.manage.system.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wcc
 * @date 2019/9/16 22:13
 */
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        //It may contain multiple IPs in header "x-forwarded-for", get first one with correct format.
        if (StringUtils.isNotBlank(ip) && ip.indexOf(",") > -1) {
            ip = ip.split(",")[0];
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    public final static String getIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");
//        logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        String loggerInfo = "getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip;
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
//                logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
                loggerInfo = "getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip;
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
//                logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
                loggerInfo = "getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip;
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
//                logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
                loggerInfo = "getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip;
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//                logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
                loggerInfo = "getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip;
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
//                logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                loggerInfo = "getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip;
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        if("127.0.0.1".equals(ip)) {
            ip = getRemoteAddr(request);
            loggerInfo = "getRemoteAddr";
        }
        logger.info("request ip : " + ip);
        logger.info("request ip type : " + loggerInfo);
        return ip;
    }

    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        String type = "X-Real-IP";
        logger.info("first remoteAddr : " + remoteAddr);
        if (StringUtils.isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
            type = "X-Forwarded-For";
        } else {
            if("127.0.0.1".equals(remoteAddr)) {
                remoteAddr = request.getHeader("X-Forwarded-For");
                type = "X-Forwarded-For";
            }
        }

        if (StringUtils.isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
            type = "Proxy-Client-IP";
        }else {
            if("127.0.0.1".equals(remoteAddr)) {
                remoteAddr = request.getHeader("Proxy-Client-IP");
                type = "Proxy-Client-IP";
            }
        }
        if (StringUtils.isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
            type = "WL-Proxy-Client-IP";
        }else {
            if("127.0.0.1".equals(remoteAddr)) {
                remoteAddr = request.getHeader("WL-Proxy-Client-IP");
                type = "WL-Proxy-Client-IP";
            }
        }
        logger.info("request.getRemoteAddr() : " + request.getRemoteAddr());
        logger.info("getRemoteAddr type : " + type);
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }
}

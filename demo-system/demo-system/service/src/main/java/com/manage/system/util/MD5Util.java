package com.manage.system.util;

import org.apache.shiro.crypto.hash.SimpleHash;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static final String PRIVATE_KEY = "chunxiao_app";

    // 全局数组
    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    @SuppressWarnings("unused")
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (byte aBByte : bByte) {
            sBuffer.append(byteToArrayString(aBByte));
        }
        return sBuffer.toString();
    }

    public static String getMD5Code(String strObj) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteToString(md.digest(strObj.getBytes(Charset.forName("UTF-8"))));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    public static String getPasswordMD5(String password) {
        //加密方式
        String algorithmName = "MD5";
        //加密的字符串
        //盐值，用于和密码混合起来用
        //加密的次数,可以进行多次的加密操作
        int hashIterations = 2;
        //通过SimpleHash 来进行加密操作
        SimpleHash hash = new SimpleHash(algorithmName, password, null, hashIterations);
        return hash.toString();
    }

    public static void main(String[] args) {
        System.out.println(getMD5Code(getMD5Code("a1234567")));
    }
}
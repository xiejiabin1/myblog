package com.xie.myblog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description: MD5加密工具类
 * @author: 谢
 * @time: 2020/6/26 19:48
 */
public class MD5Util {
    public static String encode(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte[] byteDigest = md.digest();

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }

            // 32位加密
            return buf.toString();
            // 16位加密
            // return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 测试main方法
    public static void main(String[] args) {
        System.out.println(encode("x123456"));
    }
}

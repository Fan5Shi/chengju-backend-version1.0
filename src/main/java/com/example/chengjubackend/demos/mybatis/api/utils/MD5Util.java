package com.example.chengjubackend.demos.mybatis.api.utils;

import org.apache.commons.codec.digest.DigestUtils;

//@Component
public class MD5Util {

    /**
     * MD5加密方法，可能会用到
     * @param src
     * @return
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static final String salt = "1a2b3c4d";

    public static String frontPass2BackPass(String frontPass) {
        String str = "" + salt.charAt(3) + salt.charAt(1) + frontPass + salt.charAt(4) + salt.charAt(2);
        return md5(str);
    }

    public static String backPass2DBPass(String backPass, String salt) {
        String str = "" + salt.charAt(3) + salt.charAt(1) + backPass + salt.charAt(4) + salt.charAt(2);
        return md5(str);
    }

    public static String frontPass2DBPass(String frontPass, String salt) {
        String backPass = frontPass2BackPass(frontPass);
        String dbPass = backPass2DBPass(backPass, salt);
        return dbPass;
    }

    public static void main(String args[]) {
        String frontPass = "123456";
        String backPass = frontPass2BackPass(frontPass);
        System.out.println(backPass);
        String dbPass = backPass2DBPass(backPass, salt);
        System.out.println(dbPass);
        System.out.println(frontPass2DBPass(frontPass, salt));
    }
}

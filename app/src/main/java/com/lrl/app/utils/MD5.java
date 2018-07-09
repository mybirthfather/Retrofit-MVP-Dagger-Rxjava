package com.lrl.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ZHP on 2017/7/18.
 */
public class MD5 {
	private static char md5Chars[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String md5(String str) throws Exception {
        MessageDigest md5 = getMD5Instance();
        md5.update(str.getBytes("UTF-8"));
        byte[] digest = md5.digest();
        char[] chars = toHexChars(digest);
        return new String(chars);
    }

    private static MessageDigest getMD5Instance() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ignored) {
            throw new RuntimeException(ignored);
        }
    }

    private static char[] toHexChars(byte[] digest) {
        char[] chars = new char[digest.length * 2];
        int i = 0;
        for (byte b : digest) {
            char c0 = md5Chars[(b & 0xf0) >> 4];
            chars[i++] = c0;
            char c1 = md5Chars[b & 0xf];
            chars[i++] = c1;
        }
        return chars;
    }
}

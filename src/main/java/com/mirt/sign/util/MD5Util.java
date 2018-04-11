package com.mirt.sign.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5 encode
 *
 * @authur Zhang Yuqi
 * @create 2018/4/11.
 */
public class MD5Util {
    public MD5Util() {
    }

    public static String encode(String source) {
        return encodeMd5(source.getBytes());
    }

    private static String encodeMd5(byte[] source) {
        try {
            return encodeHex(MessageDigest.getInstance("MD5").digest(source));
        } catch (NoSuchAlgorithmException var2) {
            throw new IllegalStateException(var2.getMessage(), var2);
        }
    }

    private static String encodeHex(byte[] bytes) {
        StringBuffer buffer = new StringBuffer(bytes.length * 2);

        for(int i = 0; i < bytes.length; ++i) {
            if ((bytes[i] & 255) < 16) {
                buffer.append("0");
            }

            buffer.append(Long.toString((long)(bytes[i] & 255), 16));
        }

        return buffer.toString();
    }
}

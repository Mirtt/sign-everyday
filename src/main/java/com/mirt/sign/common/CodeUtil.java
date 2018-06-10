package com.mirt.sign.common;

import java.util.Random;

/**
 * 用于生成页面验证码
 *
 * @authur zyq
 * @create 18-6-10.
 */
public class CodeUtil {
    private CodeUtil() {
    }

    public static String generateCode() {
        return String.format("%04d", new Random().nextInt(10000));
    }
}

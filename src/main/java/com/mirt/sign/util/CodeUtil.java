package com.mirt.sign.util;

import java.util.Random;

/**
 * 用于生成页面验证码
 *
 * @author Mirt
 * @date 18-6-10.
 */
public class CodeUtil {
    private CodeUtil() {
    }

    public static String generateCode() {
        return String.format("%04d", new Random().nextInt(10000));
    }
}

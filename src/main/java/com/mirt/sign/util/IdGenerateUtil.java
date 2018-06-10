package com.mirt.sign.util;

import org.springframework.util.IdGenerator;
import org.springframework.util.SimpleIdGenerator;

/**
 * 使用spring的IdGenerator生成UUID
 *
 * @authur zyq
 * @create 18-6-10.
 */
public class IdGenerateUtil {
    private static IdGenerator idGenerator = new SimpleIdGenerator();

    private IdGenerateUtil(){}

    public static Long generateId(){
        return idGenerator.generateId().node();
    }

}

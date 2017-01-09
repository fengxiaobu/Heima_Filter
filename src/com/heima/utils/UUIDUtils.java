package com.heima.utils;

import java.util.UUID;

/**
 * 生成随机的字符串的工具类：
 *
 * @author Feng
 */
public class UUIDUtils {
    /**
     * 生成随机的字符串的工具类：
     *
     * @return
     */
    public static String getUUID() {
        //去除"-"
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获得文件名
     */

    public static String getUUIDFileName(String fileName) {
        return UUID.randomUUID().toString().replace("-", "") + fileName;
    }
}

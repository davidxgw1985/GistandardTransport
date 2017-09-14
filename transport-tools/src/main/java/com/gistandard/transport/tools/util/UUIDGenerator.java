package com.gistandard.transport.tools.util;

import java.util.UUID;

/**
 * Created by m on 2016/1/15.
 */
public class UUIDGenerator {

    /**
     * 获得UUID
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        str = str.replace("-","");
        return str;
    }
}

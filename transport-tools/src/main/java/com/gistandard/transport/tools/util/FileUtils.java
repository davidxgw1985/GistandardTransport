package com.gistandard.transport.tools.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by m on 2016/9/1.
 */
public abstract class FileUtils {
    /**
     * <p>读取properties文件。</p>
     *
     * @param prop Properties对象
     * @param fileLocation 文件路径及名称
     * @throws IOException
     */
    public static void loadPropertiesFile(Properties prop, String fileLocation)
            throws IOException
    {
        InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(fileLocation);
        prop.load(is);
        is.close();
    }
}

package com.gistandard.transport.system.common.bean;

import java.util.*;

/**
 * 文件后缀转换枚举类
 * Created by sjl on 2016/1/4.
 */
public enum SysFileExtension {

    DOC_TO(".doc",".docx|.docm"), //word转换

    DOCX_TO(".docx",".doc|.docm"), //word转换

    DOCM_TO(".docm",".doc|.docx"), //已启用宏的word转换成不包含宏的普通文档

    XLS_TO(".xls",".xlsx|.xlsm"), //excel转换

    XLSX_TO(".xlsx",".xls|.xlsm"), //excel转换

    XLSM_TO(".xlsm",".xls|.xlsx"), //已启用宏的excel转换成不包含宏的普通文档

    PPT_TO(".ppt",".pptx|.pptm"), //ppt转换

    PPTX_TO(".pptx",".ppt|.pptm"), //ppt转换

    PPTM_TO(".pptm",".ppt|.pptx"), //已启用宏的ppt转换成不包含宏的普通文档

    JPG_TO(".jpg",".jpeg|.png|.bmp|.tif"), //图片格式转换

    JPEG_TO(".jpeg",".jpg|.png|.bmp|.tif"), //图片格式转换

    PNG_TO(".png",".jpg|.jpeg|.bmp|.tif"), //图片格式转换

    BMP_TO(".bmp",".jpg|.jpeg|.png|.tif"), //图片格式转换

    TIF_TO(".tif",".jpg|.jpeg|.png|.bmp"); //图片格式转换

    private String extension;// 后缀

    private String changeExtensions;// 可以转换成的后缀

    public static final HashMap<String, SysFileExtension> FILE_EXTENSION_MAP = new LinkedHashMap<String, SysFileExtension>();

    static {
        FILE_EXTENSION_MAP.put(SysFileExtension.DOC_TO.getExtension(),SysFileExtension.DOC_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.DOCX_TO.getExtension(),SysFileExtension.DOCX_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.DOCM_TO.getExtension(),SysFileExtension.DOCM_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.XLS_TO.getExtension(),SysFileExtension.XLS_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.XLSX_TO.getExtension(),SysFileExtension.XLSX_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.XLSM_TO.getExtension(),SysFileExtension.XLSM_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.PPT_TO.getExtension(),SysFileExtension.PPT_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.PPTX_TO.getExtension(),SysFileExtension.PPTX_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.PPTM_TO.getExtension(),SysFileExtension.PPTM_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.JPG_TO.getExtension(),SysFileExtension.JPG_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.JPEG_TO.getExtension(),SysFileExtension.JPEG_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.PNG_TO.getExtension(),SysFileExtension.PNG_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.BMP_TO.getExtension(),SysFileExtension.BMP_TO);
        FILE_EXTENSION_MAP.put(SysFileExtension.TIF_TO.getExtension(),SysFileExtension.TIF_TO);
    }

    SysFileExtension(String extension,String changeExtensions){
        this.extension = extension;
        this.changeExtensions = changeExtensions;
    }

    public static List<String> getChangeExtension(String extension) {
        List<String> changeExtensions = new ArrayList<String>();
        SysFileExtension s = FILE_EXTENSION_MAP.get(extension);
        if (s != null) {
            changeExtensions = Arrays.asList(s.getChangeExtensions().split("\\|"));
        }
        return changeExtensions;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getChangeExtensions() {
        return changeExtensions;
    }

    public void setChangeExtensions(String changeExtensions) {
        this.changeExtensions = changeExtensions;
    }
}

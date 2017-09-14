package com.gistandard.transport.system.upload.define;

import java.util.HashMap;

/**
 * @author yujie
 * @ClassName SysFileType
 * @Description
 * @Version 1.0
 * @Date 2015-09-02
 */
public enum UploadFileType {

    IDENTITY_POSITIVE("身份证正面", 1),
    IDENTITY_NEGATIVE("身份证反面", 2),
    PORTRAIT("头像", 3),
    OPERATE_LICENSE("经营执照", 4),
    DRIVER_LICENSE("驾驶证", 5),
    CAR_IMAGE("车辆照片", 6),
    DRIVING_IMAGE("行驶证照片", 7),
    TAX_REGISTER("税务登记证", 8),
    TRANSPORT_AGREE("道路运输许可证", 9),
    TRANSPORT_BUSINESS("道路运输营运证", 10),
    DELIVERY_CONFIRMATION("送达确认证明", 11),
    ORDER_ATTACHMENT("下单附件", 12),
    ORG_CODE_LILCENSE("组织结构代码证", 13),
    NEWS_IMG("新闻图片", 14),
    CAR_IMAGE_2("车辆照片", 15),
    IDENTITY_HALF("手持证件半身照照片", 17);


    public static final HashMap<Integer, Object> FILE_TYPE_MAP = new HashMap<Integer, Object>();

    static {
        for (UploadFileType c : UploadFileType.values()) {
            FILE_TYPE_MAP.put(c.getValue(),c.getName());
        }
    }


    private String name;

    private int value;

    UploadFileType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (UploadFileType c : UploadFileType.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }

        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

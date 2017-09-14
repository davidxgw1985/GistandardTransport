package com.gistandard.transport.system.upload.define;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yujie
 * @ClassName UploadDefine
 * @Description
 * @Version 1.0
 * @Date 2015-09-02
 */
public class UploadDefine {
    public static final String UPLOAD_ROOT_DIRECTORY = "valuePlusUpload/transportUpload";

    public static final Map<Integer, String> FILE_TYPE_MAP = new HashMap<Integer, String>();

    static {
        FILE_TYPE_MAP.put(UploadFileType.IDENTITY_POSITIVE.getValue(), UPLOAD_ROOT_DIRECTORY + "/protect");
        FILE_TYPE_MAP.put(UploadFileType.IDENTITY_NEGATIVE.getValue(), UPLOAD_ROOT_DIRECTORY + "/protect");
        FILE_TYPE_MAP.put(UploadFileType.OPERATE_LICENSE.getValue(), UPLOAD_ROOT_DIRECTORY + "/protect");
        FILE_TYPE_MAP.put(UploadFileType.DRIVER_LICENSE.getValue(), UPLOAD_ROOT_DIRECTORY + "/protect");
        FILE_TYPE_MAP.put(UploadFileType.CAR_IMAGE.getValue(), UPLOAD_ROOT_DIRECTORY + "/protect");
        FILE_TYPE_MAP.put(UploadFileType.DRIVING_IMAGE.getValue(), UPLOAD_ROOT_DIRECTORY + "/protect");
        FILE_TYPE_MAP.put(UploadFileType.TAX_REGISTER.getValue(), UPLOAD_ROOT_DIRECTORY + "/protect");
        FILE_TYPE_MAP.put(UploadFileType.TRANSPORT_AGREE.getValue(), UPLOAD_ROOT_DIRECTORY + "/protect");
        FILE_TYPE_MAP.put(UploadFileType.TRANSPORT_BUSINESS.getValue(), UPLOAD_ROOT_DIRECTORY + "/protect");
        FILE_TYPE_MAP.put(UploadFileType.PORTRAIT.getValue(), UPLOAD_ROOT_DIRECTORY + "/open");
        FILE_TYPE_MAP.put(UploadFileType.DELIVERY_CONFIRMATION.getValue(), UPLOAD_ROOT_DIRECTORY + "/open");
    }

}

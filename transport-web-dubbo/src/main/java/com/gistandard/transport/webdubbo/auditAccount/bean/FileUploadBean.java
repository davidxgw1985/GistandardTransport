package com.gistandard.transport.webdubbo.auditAccount.bean;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 附件上传信息
 */
public class FileUploadBean implements Serializable{

    // 附件
    private MultipartFile multipartFile;

    // 附件类型
    private Integer fileType;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
}

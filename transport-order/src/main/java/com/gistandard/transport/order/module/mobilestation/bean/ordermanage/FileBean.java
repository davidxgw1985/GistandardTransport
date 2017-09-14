package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: xgw
 * @ClassName: FileBean
 * @Date: 2016/12/27
 * @Description: 单个文件内容
 */
@ApiModel(description = "单个文件内容")
public class FileBean {
    @ApiModelProperty(value = "文件编号",required = true, position = 1)
    private String fileId;
    @ApiModelProperty(value = "文件内容",required = true, position = 2)
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}

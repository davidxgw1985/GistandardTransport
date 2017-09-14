package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: OrderPicUploadReq
 * @Date: 2016/12/23
 * @Description: 订单图片上传
 */
@ApiModel(description = "订单图片批量上传请求对象", parent = AppBaseRequest.class)
public class OrderPicUploadReq extends AppBaseRequest{
    private static final long serialVersionUID = -1741353382274047669L;

    @ApiModelProperty(value = "订单号",required = true, position = 1)
    private String busiBookNo;

    @ApiModelProperty(value = "文件内容",required = true, position = 2)
    private List<FileBean> list;

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public List<FileBean> getList() {
        return list;
    }

    public void setList(List<FileBean> list) {
        this.list = list;
    }

}

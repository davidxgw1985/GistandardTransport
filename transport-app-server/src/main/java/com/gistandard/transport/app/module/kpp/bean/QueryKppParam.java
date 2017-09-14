package com.gistandard.transport.app.module.kpp.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by yujie on 2016/9/30.
 */
@ApiModel(description = "查询用户KPP模块接口参数对象", parent = AppBaseRequest.class)
public class QueryKppParam extends AppBaseRequest implements ValidTokenMark {

    @ApiModelProperty(value = "KPP模块Id",required = true, position = 1)
    private Integer id;

    @ApiModelProperty(value = "KPP模块代码",required = true, position = 2)
    private String moudleCode;  //对应KPP的模块代码

    @ApiModelProperty(value = "KPP模块名称", position = 3)
    private String moudleName;  //对应KPP的模块名称

    @ApiModelProperty(value = "KPP模块接单状态(1、接单状态,0、拒绝接单)", position = 4)
    private Integer moudleStatus;   //模块接单状态 1接单状态 0拒绝接单

    @ApiModelProperty(value = "创建时间",required = false, position = 5)
    private Date createDate;

    @ApiModelProperty(value = "是否在线",required = false, position = 6)
    private Integer inOn;

    @ApiModelProperty(value = "是否共享位置",required = false, position = 7)
    private Boolean shareStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoudleCode() {
        return moudleCode;
    }

    public void setMoudleCode(String moudleCode) {
        this.moudleCode = moudleCode;
    }

    public String getMoudleName() {
        return moudleName;
    }

    public void setMoudleName(String moudleName) {
        this.moudleName = moudleName;
    }

    public Integer getMoudleStatus() {
        return moudleStatus;
    }

    public void setMoudleStatus(Integer moudleStatus) {
        this.moudleStatus = moudleStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean isShareStatus() {
        return shareStatus;
    }

    public void setShareStatus(Boolean shareStatus) {
        this.shareStatus = shareStatus;
    }
}

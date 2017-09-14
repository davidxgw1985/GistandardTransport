package com.gistandard.transport.applytobe.service;

import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.applytobe.bean.*;
import com.gistandard.transport.base.entity.bean.ComAccountRequest;
import com.gistandard.transport.base.entity.bean.ComCustomer;
import com.gistandard.transport.base.entity.bean.ComCustomerRecord;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.webdubbo.auditAccount.bean.AddRequestBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by m on 2016/4/18.
 */
public interface ApplyToBeService {

    /**
     * 根据帐户ID查询是否存在申请请求
     * @param accountId
     * @return
     */
    List<ComAccountRequest> queryApplyReqByAccountId(Integer accountId);

    /**
     * 根据企业名称 或 企业编号 或 企业编号查找企业信息
     * @param custTtl 企业简称
     * @param customNo 企业编号
     * @return
     */
    List<ComCustomer> queryCompanyByParams(String custTtl, String customNo);

    /**
     * 企业简称唯一性校验
     * @param custTtl 企业简称
     * @return
     */
    List<ComCustomerRecord> checkCustTtlUnique(String custTtl);

    /**
     * 企业编号唯一性校验
     * @param customNo 企业编号
     * @return
     */
    List<ComCustomerRecord> checkCustomNoUnique(String customNo);

    /**
     * 校验车牌是否重复
     * @param truckCode
     * @return
     */
    boolean checkTruckCodeUnique(String truckCode);

    /**
     * 备案信息表校验车牌是否重复
     * @param truckCode
     * @return
     */
    boolean checkRecordTruckCodeUnique(String truckCode);

    /**
     * 个人注册文件上传
     * @param multipartFile
     * @param fileId
     * @return
     * @throws Exception
     */
    ResultBean registerFileUpload(MultipartFile multipartFile, String fileId) throws Exception;

    /**
     * 申请成为车主
     * @param carOwnerBean
     * @param accountId
     * @throws Exception
     */
    ResultBean carOwner(MerchantCarOwnerBean carOwnerBean, Integer accountId, AddRequestBean addRequestBean) throws Exception;

    /**
     * 申请成为快递员
     * @param accountId
     * @throws Exception
     */
    ResultBean courier(MerchantCourierBean merchantCourierBean, Integer accountId, AddRequestBean addRequestBean) throws Exception;

    /**
     * 申请成为咪站
     * @param accountId
     * @throws Exception
     */
    ResultBean mstation(MerchantMstationBean mstationBean, Integer accountId, AddRequestBean addRequestBean) throws Exception;

    /**
     * 申请成为车队
     * @param fleetBean
     * @param accountId
     * @throws Exception
     */
    ResultBean fleet(MerchantFleetBean fleetBean, Integer accountId, AddRequestBean addRequestBean) throws Exception;

    /**
     * 申请成为站点
     * @param stationBean
     * @param accountId
     * @throws Exception
     */
    ResultBean station(MerchantStationBean stationBean, Integer accountId, AddRequestBean addRequestBean) throws Exception;

    /**
     * 申请成为业务中心
     * @param serviceCenterBean
     * @param accountId
     * @throws Exception
     */
    ResultBean serviceCenter(ServiceCenterBean serviceCenterBean, Integer accountId, AddRequestBean addRequestBean) throws Exception;

    /**
     * 申请成为业务中心或者商业中心
     * @param accountRole
     * @param accountId
     * @throws Exception
     */
    ResultBean center(String accountRole, Integer accountId, AddRequestBean addRequestBean) throws Exception;

    /**
     * 查看申请结果
     * @param comAccountRequest
     */
    void viewApplyResult(ComAccountRequest comAccountRequest);

    /**
     * 实名认证
     * @param merchantPersonalBean
     * @throws Exception
     */
    ResultBean userAuthentication(MerchantPersonalBean merchantPersonalBean,AppLoginInfo appLoginInfo) throws Exception;

    ResultBean registerFileUpload(MultipartFile multipartFile, String fileId, int accountId) throws Exception;
}

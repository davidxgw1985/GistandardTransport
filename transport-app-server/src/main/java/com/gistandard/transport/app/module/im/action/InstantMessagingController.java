package com.gistandard.transport.app.module.im.action;

import com.gistandard.transport.app.module.im.bean.QueryContactsDetailParam;
import com.gistandard.transport.app.module.im.bean.QueryContactsDetailResult;
import com.gistandard.transport.app.module.im.bean.QueryContactsListParam;
import com.gistandard.transport.app.module.im.bean.QueryContactsListResult;
import com.gistandard.transport.app.module.im.service.InstantMessagingService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.tools.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by yujie on 2016/9/30.
 */
@Controller
@RequestMapping(value = AppServerDefine.IM_URL)
@ApiIgnore
public class InstantMessagingController extends BaseController {

    private static final String TAG_DESC = "帐号信息查询接口";

    @Autowired
    private InstantMessagingService instantMessagingService;

    @RequestMapping(value = "/queryContactsDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询联系人详情-V1.0.1", httpMethod = "POST", response = QueryContactsDetailResult.class,
            tags = TAG_DESC,
            produces = "application/json", notes = "参数: reqId{请求reqId,Android请求需要IOS不需要}<br>" +
            "accountId{ 账户ID }<br>" +
            "contactAccountId{ 联系人账户ID }")
    @ResponseBody
    public QueryContactsDetailResult queryContactsDetail(@RequestBody QueryContactsDetailParam queryContactsDetailParam) {
        QueryContactsDetailResult appBaseResult = new QueryContactsDetailResult();
        if (queryContactsDetailParam == null) {
            appBaseResult.setRetCode(0);
            appBaseResult.setRetMsg("请求参数不能为空！");
        } else {
            appBaseResult = instantMessagingService.queryContactsDetail(queryContactsDetailParam);
        }
        return appBaseResult;
    }

    /**
     * 根据账号、昵称查找账号列表
     * @param queryContactsListParam
     */
    @RequestMapping(value = "/queryContactsList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "根据账号、昵称查找账号列表-V1.0.1", httpMethod = "POST", response = QueryContactsListResult.class,
            tags = TAG_DESC,
            produces = "application/json", notes = "参数: reqId{请求reqId,Android请求需要IOS不需要}<br>" +
            "accountId{ 账户ID }<br>" +
            "startRecord{ 开始条数 ,从0开始，传0表示返回全部}<br>" +
            "pageSize {每页条数,默认为10条，为0则返回全部}<br>" +
            "param { 关键字 }")
    public QueryContactsListResult queryContactsList(@RequestBody QueryContactsListParam queryContactsListParam) {
        QueryContactsListResult res = new QueryContactsListResult();
        if (queryContactsListParam == null || StringUtil.isEmpty(queryContactsListParam.getParam())) {
            res.setRetCode(0);
            res.setRetMsg("请求参数不能为空！");
        } else {
            res = instantMessagingService.queryContactsList(queryContactsListParam);
        }
        return res;
    }

}

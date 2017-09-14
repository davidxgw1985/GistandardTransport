<input type="hidden" id="userInfoStatus" name="userInfoStatus" value="${merchantPersonalBean.userInfoStatus}"/>
<input type="hidden" name="picIdentityNo" id="picIdentityNo" value="">
<input type="hidden" name="picUserName" id="picUserName" value="">
<#if merchantPersonalBean.userInfoStatus  == 0 || merchantPersonalBean.userInfoStatus ==1>
<p class="item-title">实名认证</p>
</#if>
<ul>
    <#if merchantPersonalBean.userInfoStatus  == 0 || merchantPersonalBean.userInfoStatus ==1>
        <li class="item-img">
            <div id="identityPositiveSelect" class="upload-img up-img">
                <span class="up-status"></span>
            </div>
            <p>身份证正面照（必填）</p>
            <input type="hidden" class="" id="identityPositiveFileId" name="identityPositiveFileId"
                   value="${merchantPersonalBean.identityPositiveFileId}">
            <input type="hidden" id="identityPositiveUrl" name="identityPositiveUrl"
                   value="${merchantPersonalBean.identityPositiveUrl}">
        </li>
        <li class="item-img">
            <div id="identityNegativeSelect" class="upload-img cardnum-img">
                <span class="up-status"></span>
            </div>
            <p>身份证反面照（必填）</p>
            <input type="hidden" class="" id="identityNegativeFileId" name="identityNegativeFileId"
                   value="${personalInfo.identityPositiveFileId}">
            <input type="hidden" id="identityNegativeUrl" name="identityNegativeUrl"
                   value="${personalInfo.identityNegativeUrl}">
        </li>
        <li class="item-img">
            <div id="identityHalfSelect" class="upload-img hand-img">
                <span class="up-status"></span>
            </div>
            <p>手持身份证半身照（必填）</p>
            <input type="hidden" class="" id="identityHalfFileId" name="identityHalfFileId"
                   value="${personalInfo.identityHalfFileId}">
            <input type="hidden" id="identityHalfUrl" name="identityHalfUrl" value="${personalInfo.identityHalfUrl}">
        </li>
    </#if>

    <li class="item-inp ">
    <#if merchantPersonalBean.userInfoStatus  == 1>
        <input type="text" readonly class="" maxlength="18" placeholder="身份证号 (必填) " name="identno" id="identno"
               value="${merchantPersonalBean.identno}">
    <#elseif merchantPersonalBean.userInfoStatus ==2>
    <#else>
        <input type="text" class="" maxlength="18" placeholder="身份证号 (必填) " name="identno" id="identno"
               value="${merchantPersonalBean.identno}">
    </#if>
    </li>

    <li class="item-inp ">
    <#if merchantPersonalBean.userInfoStatus  == 1>
        <input type="text" readonly class="" maxlength="200" placeholder="真实姓名 (必填) " name="realName" id="realName"
               value="${merchantPersonalBean.realName}">
    <#elseif merchantPersonalBean.userInfoStatus ==2>
    <#else>
        <input type="text" class="" maxlength="200" placeholder="真实姓名 (必填) " name="realName" id="realName"
               value="${merchantPersonalBean.realName}">
    </#if>
    </li>
</ul>
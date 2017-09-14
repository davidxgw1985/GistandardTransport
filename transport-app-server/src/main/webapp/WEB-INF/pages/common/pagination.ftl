<#if grid?? && grid.pagination = 1 && grid.recordCount gt 0>
<div class="pages float_r" id="paginationDiv_${grid.gridId}">
    <input type="hidden" id="pageIndex_${grid.gridId}" name="pageIndex_${grid.gridId}" value="${grid.pageIndex}">
    <input type="hidden" id="pageCount_${grid.gridId}" name="pageCount_${grid.gridId}" value="${grid.pageCount}">
    <input type="hidden" id="recordCount_${grid.gridId}" name="recordCount_${grid.gridId}" value="${grid.recordCount}">
    <input type="hidden" id="pageSize_${grid.gridId}" name="pageSize_${grid.gridId}" value="${grid.pageSize}">
    <input type="hidden" id="pagination_${grid.gridId}" name="pagination_${grid.gridId}" value="${grid.pagination}">
    <ul class="float_r">
        <li>查询出<em>${grid.recordCount}</em>条记录 </li>
        <li class="pagination">
            <#if grid.pageCount lt 5>
                <#list 1..grid.pageCount as curIndex>
                    <a class="${(curIndex = grid.pageIndex)?string("cur","")}" aType="pageNum" data-value="${curIndex}" href="javascript:void(0)">${curIndex}</a>
                </#list>
            <#else >
                <#assign before = 2, after = 2, start = 1, end = grid.pageCount />
                <#if (grid.pageIndex - before) lt 1>
                    <#assign start = 1 />
                <#else >
                    <#assign start = (grid.pageIndex - before) />
                </#if>
                <#if (grid.pageIndex + after) gt grid.pageCount>
                    <#assign end = grid.pageCount />
                <#else >
                    <#assign end = (grid.pageIndex + after) />
                </#if>
                <#if (end-start+1) lt (before+after+1)>
                    <#if start = 1>
                        <#assign end = (end + ((before+after+1)-(end-start+1))) />
                        <#if end gt grid.pageCount>
                            <#assign end = grid.pageCount  />
                        </#if>
                    </#if>
                    <#if end = grid.pageCount>
                        <#assign start = (start-((before+after+1)-(end-start+1))) />
                        <#if start lt 1>
                            <#assign start = 1  />
                        </#if>
                    </#if>
                </#if>
                <a class="${(grid.pageIndex gt 1)?string("","pre_disabled")}" aType="prev"  href="javascript:void(0)">&lt;</a>
                <#list start..end as curIndex>
                    <a class="${(curIndex = grid.pageIndex)?string("cur","")}" aType="pageNum" data-value="${curIndex}" href="javascript:void(0)">${curIndex}</a>
                </#list>
                <a class="${(grid.pageIndex lt grid.pageCount)?string("next","pre_disabled")}" aType="next" href="javascript:void(0)">&gt;</a>
            </#if>
        </li>
        <li>共<em>${grid.pageCount}</em>页，到第<input type="text" value="1" name="gotoPageNum" autocomplete="off" maxlength="4" class="pnum">页</li>
        <li><a href="javascript:void(0)" name="gotoBtn" class="btn_sure"><em>确 定</em></a></li>
    </ul>
</div>
<script type="text/javascript">
    $(function(){
        $.fn.transport.transGrid({
            gridId : "${grid.gridId}",
            loadUrl : "${grid.loadUrl}",
            pageSize: "${grid.pageSize}"
            <#if grid.getParamFunc != ""> ,getParamFunc : "${grid.getParamFunc}"</#if>
            <#if grid.loadCallBack != ""> ,loadCallBack : "${grid.loadCallBack}"</#if>
        });
    });
</script>
</#if>
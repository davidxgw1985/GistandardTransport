package com.gistandard.transport.base.bean.grid;

import java.util.List;

/**
 * @author yujie
 * @ClassName GridBean
 * @Description
 * @Version 1.0
 * @Date 2015-07-15
 */
public class GridBean<k> {

    /**
     * 表格ID
     */
    private String gridId;

    /**
     * 表格加载地址
     */
    private String loadUrl;

    /**
     * 表格获取出參的函数名称
     */
    private String getParamFunc;

    /**
     * 表格回调函数名称
     */
    private String loadCallBack;

    /**
     * 表格数据
     */
    private List<k> data;

    /**
     * 每页显示条数
     */
    private int pageSize = 10;

    /**
     * 当前页码 ，默认第一页
     */
    private int pageIndex = 1;

    /**
     * 总页数
     */
    private int pageCount = 0;

    /**
     * 数据总数
     */
    private int recordCount = 0;

    /**
     * 翻页查询起始条数
     */
    private int startRecord;

    /**
     * 是否需要分页, 1 :分页  0：不分页，默认分页
     */
    private int pagination = 1;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        if(pagination == 1){
            pageCount = (recordCount + pageSize -1) / pageSize ;
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPagination() {
        return pagination;
    }

    public void setPagination(int pagination) {
        this.pagination = pagination;
    }

    public int getStartRecord() {
        if(pagination == 1){
            startRecord = (pageIndex - 1) * pageSize;
        }
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public String getLoadCallBack() {
        return loadCallBack;
    }

    public void setLoadCallBack(String loadCallBack) {
        this.loadCallBack = loadCallBack;
    }

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId;
    }

    public String getLoadUrl() {
        return loadUrl;
    }

    public void setLoadUrl(String loadUrl) {
        this.loadUrl = loadUrl;
    }

    public String getGetParamFunc() {
        return getParamFunc;
    }

    public void setGetParamFunc(String getParamFunc) {
        this.getParamFunc = getParamFunc;
    }
}

package com.gistandard.transport.base.bean.app;

/**
 * @author: xgw
 * @ClassName: BaseResPageBean
 * @Date: 2015/12/24
 * @Description: 分页返回基类
 */
public class BaseResPageBean extends BaseResBean {
    private static final long serialVersionUID = -1530929977119476045L;

    private int recordCount;//总条数

    public BaseResPageBean(){
        super();
    }

    public BaseResPageBean(BaseReqPageBean reqBean) {
        super(reqBean);
    }


    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
}

package com.gistandard.transport.quote.system.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shenzhijun on 2016/2/24.
 */
public class QuoteItemQueryBean implements Serializable {
    private static final long serialVersionUID = 6032238567811449350L;

    private Integer id;
    //条目类型（0、物流，1、运输，2、快递）
    private Integer itemType ;

    //多个条目类型（1,2）
    private List<Integer> itemTypeList ;

    //条目类别（1、产品，2、服务）
    private Integer itemCategory;

    private String roleId;



    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Integer itemCategory) {
        this.itemCategory = itemCategory;
    }

    public List<Integer> getItemTypeList() {
        return itemTypeList;
    }

    public void setItemTypeList(List<Integer> itemTypeList) {
        this.itemTypeList = itemTypeList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

package com.gistandard.transport.base.entity.bean;

/**
 * Created by yujie on 15/10/17.
 */
public class ComCustomerRelation {

    private Integer id;

    private Integer customId;

    private Integer parentCustomId;

    private String parentPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomId() {
        return customId;
    }

    public void setCustomId(Integer customId) {
        this.customId = customId;
    }

    public Integer getParentCustomId() {
        return parentCustomId;
    }

    public void setParentCustomId(Integer parentCustomId) {
        this.parentCustomId = parentCustomId;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }
}

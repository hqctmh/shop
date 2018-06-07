package cn.mh.entity;

import java.util.Date;

public class ReturnMain {
    private String id;

    private String indentId;

    private String storeIndentId;

    private Date createTime;

    private Date updateTime;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId == null ? null : indentId.trim();
    }

    public String getStoreIndentId() {
        return storeIndentId;
    }

    public void setStoreIndentId(String storeIndentId) {
        this.storeIndentId = storeIndentId == null ? null : storeIndentId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}
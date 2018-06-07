package cn.mh.entity.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderModel implements Serializable {
    private String OrderNum;

    private String storeName;

    private String statusName;

    private String statusValue;

    private String storeIndentId;

    private Date createTime;

    private List<IndentGoodsModel> modelList;

    private List<DoModel> doModelList;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<DoModel> getDoModelList() {
        return doModelList;
    }

    public void setDoModelList(List<DoModel> doModelList) {
        this.doModelList = doModelList;
    }

    public String getStoreIndentId() {
        return storeIndentId;
    }

    public void setStoreIndentId(String storeIndentId) {
        this.storeIndentId = storeIndentId;
    }

    public String getOrderNum() {
        return OrderNum;
    }

    public void setOrderNum(String orderNum) {
        OrderNum = orderNum;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public List<IndentGoodsModel> getModelList() {
        return modelList;
    }

    public void setModelList(List<IndentGoodsModel> modelList) {
        this.modelList = modelList;
    }



}

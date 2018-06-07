package cn.mh.entity.model;

import cn.mh.entity.Goods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ReturnModel implements Serializable {

    private String returnMainId;

    private String StoreIndentId;

    private String storeName;

    private String statusName;

    private String statusValue;

    private String orderNum;

    private BigDecimal price;

    private Date createTime;

    private String createTimeStr;

    private List<DoModel> doModelList;

    private List<Goods> goodsList;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<DoModel> getDoModelList() {
        return doModelList;
    }

    public void setDoModelList(List<DoModel> doModelList) {
        this.doModelList = doModelList;
    }

    public String getReturnMainId() {
        return returnMainId;
    }

    public void setReturnMainId(String returnMainId) {
        this.returnMainId = returnMainId;
    }

    public String getStoreIndentId() {
        return StoreIndentId;
    }

    public void setStoreIndentId(String storeIndentId) {
        StoreIndentId = storeIndentId;
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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}

package cn.mh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IndentView implements Serializable{
    private String goodsId;

    private Integer detailAmount;

    private String storeIndentId;

    private String storeId;

    private String orderNum;

    private Integer storeIndentAmount;

    private BigDecimal price;

    private String status;

    private String returnStatus;

    private Date createTime;

    private Date updateTime;

    private String indentId;

    private String userId;

    private String addressId;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public Integer getDetailAmount() {
        return detailAmount;
    }

    public void setDetailAmount(Integer detailAmount) {
        this.detailAmount = detailAmount;
    }

    public String getStoreIndentId() {
        return storeIndentId;
    }

    public void setStoreIndentId(String storeIndentId) {
        this.storeIndentId = storeIndentId == null ? null : storeIndentId.trim();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Integer getStoreIndentAmount() {
        return storeIndentAmount;
    }

    public void setStoreIndentAmount(Integer storeIndentAmount) {
        this.storeIndentAmount = storeIndentAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus == null ? null : returnStatus.trim();
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

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId == null ? null : indentId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId == null ? null : addressId.trim();
    }
}
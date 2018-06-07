package cn.mh.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class IndentDetail implements Serializable{
    private String id;

    private String indentId;

    private String storeIndentId;

    private String goodsId;

    private Integer amount;

    private BigDecimal price;

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

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
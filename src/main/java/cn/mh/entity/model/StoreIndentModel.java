package cn.mh.entity.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class StoreIndentModel implements Serializable {
    private int amount;

    private BigDecimal price;

    public String storeIndentId;

    public String getStoreIndentId() {
        return storeIndentId;
    }

    public void setStoreIndentId(String storeIndentId) {
        this.storeIndentId = storeIndentId;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

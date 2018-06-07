package cn.mh.entity.model;

import java.math.BigDecimal;
import java.util.List;

public class AdminStatisticsModel {

    private String Name;

    private List<BigDecimal> price;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<BigDecimal> getPrice() {
        return price;
    }

    public void setPrice(List<BigDecimal> price) {
        this.price = price;
    }
}

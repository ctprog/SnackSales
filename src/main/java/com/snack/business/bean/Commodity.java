package com.snack.business.bean;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

public class Commodity {
    private Integer cId;

    @Length(min = 2,max = 80,message = "商品名称不合法")
    private String cName;

    private BigDecimal cPrice;

    private Integer tId;

    private CommodityType comType;

    private List<Img> imgs;

    public List<Img> getImgs() {
        return imgs;
    }

    public void setImgs(List<Img> imgs) {
        this.imgs = imgs;
    }

    public CommodityType getComType() {
        return comType;
    }

    public void setComType(CommodityType comType) {
        this.comType = comType;
    }

    private List<String> images;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public BigDecimal getcPrice() {
        return cPrice;
    }

    public void setcPrice(BigDecimal cPrice) {
        this.cPrice = cPrice;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cPrice=" + cPrice +
                ", tId=" + tId +
                ", comType=" + comType +
                ", images=" + images +
                '}';
    }
}
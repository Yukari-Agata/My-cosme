package com.example.cosme.domain;

public class Cosmetic {
    private Integer id;
    private String name;
    private Integer brandId;
    private String brandName;
    private Integer categoryId;
    private String categoryName;
    private Integer priceType;
    private Integer deletedFlag;
    private Integer repeatFlag;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getBrandId() {
        return brandId;
    }
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public Integer getPriceType() {
        return priceType;
    }
    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }
    public Integer getDeletedFlag() {
        return deletedFlag;
    }
    public void setDeletedFlag(Integer deletedFlag) {
        this.deletedFlag = deletedFlag;
    }
    public Integer getRepeatFlag() {
        return repeatFlag;
    }
    public void setRepeatFlag(Integer repeatFlag) {
        this.repeatFlag = repeatFlag;
    }
    
    @Override
    public String toString() {
        return "Cosmetic [id=" + id + ", name=" + name + ", brandId=" + brandId + ", brandName=" + brandName
                + ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", priceType=" + priceType
                + ", deletedFlag=" + deletedFlag + ", repeatFlag=" + repeatFlag + "]";
    }
}

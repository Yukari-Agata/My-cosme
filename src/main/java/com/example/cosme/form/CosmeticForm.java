package com.example.cosme.form;

public class CosmeticForm {
    // 商品名
    private String name;
    // ブランドID
    private Integer brandId;
    // カテゴリID
    private Integer categoryId;
    // 0 = プチプラ
    // 1 = ハイブラ
    private Integer priceType;

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
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Integer getPriceType() {
        return priceType;
    }
}

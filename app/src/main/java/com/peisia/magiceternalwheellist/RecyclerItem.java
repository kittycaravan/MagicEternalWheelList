package com.peisia.magiceternalwheellist;
public class RecyclerItem {
    private String categoryName;
    public RecyclerItem(String categoryName) {
        this.categoryName = categoryName;
    }
    // 복사 생성자
    public RecyclerItem(RecyclerItem r) {
        this.categoryName = r.getCategoryName();
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

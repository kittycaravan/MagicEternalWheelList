package com.peisia.magiceternalwheellist;
public class RecyclerItem {
    private String chId;
    private String imgUrl;
    public RecyclerItem(String chId, String imgUrl) {
        this.chId = chId;
        this.imgUrl = imgUrl;
    }
    public String getChId() {
        return chId;
    }
    public String getImgUrl() {
        return imgUrl;
    }
}

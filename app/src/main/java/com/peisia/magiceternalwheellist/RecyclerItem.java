package com.peisia.magiceternalwheellist;
public class RecyclerItem {
    private String chId;
    private String imgUrl;
    public RecyclerItem(String chId, String imgUrl) {
        if(chId == null){
            chId = "";
        }
        this.chId = chId;
        if(imgUrl == null){
            imgUrl = "";
        }
        this.imgUrl = imgUrl;
    }
    public String getChId() {
        return chId;
    }
    public String getImgUrl() {
        return imgUrl;
    }
}

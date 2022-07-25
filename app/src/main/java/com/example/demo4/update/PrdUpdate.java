package com.example.demo4.update;

public class PrdUpdate {
    String pId,name,price,description;

    public PrdUpdate() {
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PrdUpdate(String pId, String name, String price, String description) {
        this.pId = pId;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}

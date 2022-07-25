package com.example.demo4.update;

public class ServerResponseUpdate {
  private   PrdUpdate PRODUCT;
  private  String message;

    public PrdUpdate getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(PrdUpdate PRODUCT) {
        this.PRODUCT = PRODUCT;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

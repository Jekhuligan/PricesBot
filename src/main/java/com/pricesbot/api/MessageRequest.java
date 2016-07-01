package com.pricesbot.api;

import java.math.BigDecimal;

/**
 * Created by Jek on 01.07.16.
 */
public class MessageRequest
{
    private Integer userId;
    private String shop;
    private String item;
    private BigDecimal price;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUserId() {

        return userId;
    }

    public String getShop() {
        return shop;
    }

    public String getItem() {
        return item;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

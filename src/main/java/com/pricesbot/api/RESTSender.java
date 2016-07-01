package com.pricesbot.api;

import java.math.BigDecimal;

/**
 * Created by Jek on 24.06.16.
 */
public interface RESTSender
{
    public String sendRest();
    public String sendPurchase(Integer id, String shop,
                               String item, BigDecimal price);
}

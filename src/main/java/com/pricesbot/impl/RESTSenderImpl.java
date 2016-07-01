package com.pricesbot.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pricesbot.api.MessageRequest;
import com.pricesbot.api.RESTSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.pricesbot.TelegramBotConstant;


import java.math.BigDecimal;

/**
 * Created by Jek on 24.06.16.
 */
@Service
public class RESTSenderImpl implements RESTSender
{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String sendRest() {
        return null;
    }

    public String sendPurchase(Integer id, String shop,
                               String item, BigDecimal price)
    {

        MessageRequest message = new MessageRequest();
        message.setUserId(id);
        message.setShop(shop);
        message.setItem(item);
        message.setPrice(price);

        ObjectMapper objectMapper = new ObjectMapper();
        String request = null;
        try {
            request = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> entity = new HttpEntity<String>(request,headers);

        ResponseEntity<String> response = restTemplate.exchange(TelegramBotConstant.URL_SERVER + "/input/", HttpMethod.POST, entity, String.class);

        return response.getBody();

    }

}

package com.pricesbot.impl;

import com.pricesbot.api.RESTSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Contact;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import com.pricesbot.TelegramBotConstant;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by Jek on 30.05.16.
 */
@Component
public class BotImpl extends TelegramLongPollingBot  {


    @Autowired
    private RESTSender restSender;

    @Override
    public void onUpdateReceived(Update update) {



        if(update.hasMessage()){
            Message message = update.getMessage();
            User user = message.getFrom();



            Contact contact = message.getContact();


            if(message.hasText()){

                //create a object that contains the information to send back the message
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString()); //who should get the message? the sender from which we got the message...
                if(isCheckFormat(message.getText(),user))
                {
                    sendMessageRequest.setText(getRESTresponse());
                }
                else
                {
                    sendMessageRequest.setText("Здравствуйте "+ user.getFirstName() + " введите информацию в формате: магазин-товар-цена");
                }
//                if(message.getText().contains("1"))
//                    sendMessageRequest.setText("Hello "+ user.getFirstName() + " Number");
//                else
//                    if (restSender != null)
//                     sendMessageRequest.setText(restSender.sendRest());
//                        else
//                        sendMessageRequest.setText("Здравствуйте "+ user.getFirstName() + "введиет информацию в формате: магазин.товар.цена");

                try {
                    sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
                } catch (TelegramApiException e) {
                    //do some error handling
                }//end catch()
            }//end if()
        }//end  if()

    }//end onUpdateReceived()



    @Override
    public String getBotUsername() {
        return TelegramBotConstant.USERNAME_TELEGRAM_BOT;
    }

    @Override
    public String getBotToken() {
        return TelegramBotConstant.TOKEN_TELEGRAM_BOT;
    }


    private String restResponse;
    private void setRESTresponse(String text)
    {
        this.restResponse = text;
    }
    private String getRESTresponse()
    {
        return this.restResponse;
    }

    private boolean isCheckFormat(String text, User user)
    {
        String[] item = text.split("-");
        if (item.length == 3)
        {
            String shop = item[0];
            String purchase = item[1];
            BigDecimal price = new BigDecimal(item[2]);
            setRESTresponse(restSender.sendPurchase(user.getId(),shop,purchase,price));
            return true;
        }
        return false;
    }

    private String outputDialog()
    {
        return "Вы добавили покупку в " + new Date(System.currentTimeMillis());
    }
}

package impl;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Contact;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import telegrambot.TelegremBotConstant;

/**
 * Created by Jek on 30.05.16.
 */
public class BotImpl extends TelegramLongPollingBot
{
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
                if(message.getText().contains("1"))
                    sendMessageRequest.setText("Hello "+ user.getFirstName() + " Number");
                else
                    sendMessageRequest.setText("Hello "+ user.getFirstName() + " you said: " + message.getText());

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
        return TelegremBotConstant.USERNAME_TELEGRAM_BOT;
    }

    @Override
    public String getBotToken() {
        return TelegremBotConstant.TOKEN_TELEGRAM_BOT;
    }
}

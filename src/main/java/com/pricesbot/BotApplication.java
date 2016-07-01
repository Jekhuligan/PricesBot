package com.pricesbot;

import com.pricesbot.impl.BotImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

/**
 * Created by Jek on 24.06.16.
 */
@SpringBootApplication
public class BotApplication
{

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BotApplication.class, args);
    }
}


@Component
class StartBot implements CommandLineRunner
{

   @Autowired
   private BotImpl botimpl;

    @Override
    public void run(String... strings) throws Exception
    {

        TelegramBotsApi tBot = new TelegramBotsApi();

        try
        {
            tBot.registerBot(botimpl);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }


    }
}

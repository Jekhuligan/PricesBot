package telegrambot;

import impl.BotImpl;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

/**
 * Created by Jek on 19.04.16.
 */
public class Main
{

    public static void main(String []args)
    {

        TelegramBotsApi tBot = new TelegramBotsApi();


        try
        {
            tBot.registerBot(new BotImpl());
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }


    }
}

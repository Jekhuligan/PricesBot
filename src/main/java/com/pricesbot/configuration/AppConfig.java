package com.pricesbot.configuration;

import com.pricesbot.api.RESTSender;
import com.pricesbot.impl.BotImpl;
import com.pricesbot.impl.RESTSenderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Jek on 24.06.16.
 */
@Configuration
public class AppConfig
{
        @Bean
        public RestTemplate restTemplate()
        {
            return new RestTemplate();
        }
}

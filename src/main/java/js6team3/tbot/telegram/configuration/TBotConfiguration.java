package js6team3.tbot.telegram.configuration;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The TBot configuration and connect
 * @author Dmitry
 */
@Configuration
public class TBotConfiguration {

    @Bean
    public TelegramBot telegramBot(@Value("${telegram.bot.token}") String token) {
        return new TelegramBot(token);
    }
}
package js6team3.tbot.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import js6team3.tbot.telegram.constant.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * The Telegram bot update
 *
 * @author zalex14
 */
@Component
public class TBotListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TBotListener.class);
    private final TelegramBot telegramBot;

    public TBotListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        try {
            updates.stream()
                    .filter(update -> update.message() != null)
                    .forEach(update -> {
                        logger.info("Handles update: {}", update);              // Process your updates
                        Message message = update.message();
                        Long chatId = message.chat().id();                      // chat id
                        Long usrId = message.from().id();                       // user id
                        String text = message.text();

                        if ("/start".equals(text)) {
                            SendMessage sendMessage = new SendMessage(chatId,
                                    InfoConstant.INFO1 + TBotMenu.GREETING +
                                            "\n [Ваш chatId: " + chatId + " usrId: " + usrId + "]");
                            SendResponse sendResponse = telegramBot.execute(sendMessage);

                            if (!sendResponse.isOk()) {
                                logger.error("Error: {}", sendResponse.description());
                            }
//                    sendMessage = new SendMessage(chatId, TBotConstant.SELECT_MENU);
//                    sendResponse = telegramBot.execute(sendMessage);
                        }
                    });

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
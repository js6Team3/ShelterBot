package js6team3.tbot.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import js6team3.tbot.constant.InfoConstant;
import js6team3.tbot.constant.TBotMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Telegram bot update
 *
 * @author zalex14
 */
@Component
@Data
@AllArgsConstructor
public class TBotListener implements UpdatesListener {
    //    private final BotService botService;
    private final Logger logger = LoggerFactory.getLogger(TBotListener.class);
    private final TelegramBot telegramBot;
    private final Pattern pattern = Pattern.compile("\\d{1,2}");

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Process the bot updates
     *
     * @param updates the bot iteration
     * @return userId, chartId, message
     */
    @Override
    public int process(List<Update> updates) {
        try {
            updates.stream()
                    .filter(update -> update.message() != null)
                    .forEach(update -> {
                        logger.info("Handles update: {}", update);              // Process your updates
                        Message message = update.message();
                        Long chatId = message.chat().id();                      // chat id
                        Long userId = message.from().id();                       // user id
                        String text = message.text();

                        if ("/start".equals(text)) {
                            sendMessage(chatId, InfoConstant.INFO1 + TBotMenu.GREETING +
                                    "\n [Ваш chatId: " + chatId + " usrId: " + userId + "]");
                        } else if (text != null) {
                            Matcher matcher = pattern.matcher(text);
                            if (matcher.find()) {
                               sendMessage(chatId, TBotMenu.SELECT_MENU);
                            }
                        } else {
                                sendMessage(chatId, "Некорректный формат меню");
                            }
        });
    } catch(Exception e)  {logger.error(e.getMessage(), e);}
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }

    /**
     * @param chatId  The telegram chat id
     * @param message The telegram message
     */
    private void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error: {}", sendResponse.description());
        }
    }
}
package js6team3.tbot.telegram.timer;

import com.pengrad.telegrambot.TelegramBot;;
import js6team3.tbot.service.CatService;
import js6team3.tbot.service.DogService;
import js6team3.tbot.telegram.entity.Message;
import js6team3.tbot.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class TBotTimer {
    private final TelegramBot telegramBot;
//    private final MessageService messageService;
    private final CatService catService;
    private final DogService dogService;


}
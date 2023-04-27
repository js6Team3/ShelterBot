package js6team3.tbot.repository.bot;

import js6team3.tbot.entity.bot.BotMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository: Bot
 * Implement bot access layer for the telegram
 *
 * @author zalex14
 * @version 1.0
 */
public interface BotRepository extends JpaRepository<BotMessage, Long> {

}
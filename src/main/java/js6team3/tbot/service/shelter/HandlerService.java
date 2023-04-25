package js6team3.tbot.service.shelter;

import js6team3.tbot.entity.shelter.Handler;
import js6team3.tbot.exception.NullValueException;
import js6team3.tbot.listener.TBotListener;
import js6team3.tbot.repository.shelter.HandlerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Service for managing the recommended handlers for dogs
 *
 * @author zalex14
 */
@Service
public class HandlerService {
    private final Logger logger = LoggerFactory.getLogger(TBotListener.class);

    private final HandlerRepository handlerRepository;

    public HandlerService(HandlerRepository handlerRepository) {
        this.handlerRepository = handlerRepository;
    }

    /**
     * Add new handler for a dog
     *
     * @param handler obj
     */
    public Handler add(Handler handler) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Current Method is - " + methodName);

        if (handler.getSurname().isBlank() || handler.getSurname() == null) {
            throw new NullValueException("Имя кинолога не задано");
        }
        return this.handlerRepository.save(handler);
    }

    /**
     * Remove the handler
     *
     * @param id The handler id
     */
    public Handler delete(Long id) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Current Method is - " + methodName);
        Handler handler = handlerRepository.findById(id).orElse(null);
        handlerRepository.deleteById(id);
        return handler;
    }

    /**
     * Update the handler data
     *
     * @param id      the handler id
     * @param handler obj
     */
    public Handler update(Long id, Handler handler) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Current Method is - " + methodName);

        Handler updateHandler = handlerRepository.findById(id).orElse(null);
        if (updateHandler != null) {
            updateHandler.setSurname(handler.getSurname());
            updateHandler.setSurname(handler.getSurname());

        } else {
            throw new NullValueException("Недостаточно данных для обновления информации о приюте");
        }
        return handlerRepository.save(updateHandler);

    }

    /**
     * Outpoint of handlers' list
     */
    public Collection<Handler> getAllHandlers() {
        return this.handlerRepository.findAll();
    }

    /**
     * The handler's info
     */
    public Handler getHandler(Long id) {
        return handlerRepository.findById(id).orElse(null);
    }
}
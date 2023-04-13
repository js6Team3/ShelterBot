package js6team3.tbot.service;

import js6team3.tbot.entity.Cat;
import js6team3.tbot.entity.Dog;

/**
 * Сервис валидации
 * @author Юрий Калынбаев
 */
public interface ValidationService {

    /**
     * Валидация экземпляра сущности "Кот"
     *
     * @param cat объект для валидации
     * @return true - объект корректный
     */
    public boolean validate(Cat cat);

    /**
     * Валидация экземпляра сущности "Собака"
     *
     * @param dog объект для валидации
     * @return true - объект корректный
     */
    public boolean validate(Dog dog);

}

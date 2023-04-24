package js6team3.tbot.service;

import js6team3.tbot.entity.Dog;
import js6team3.tbot.exception.CatNullParameterValueException;
import js6team3.tbot.entity.Cat;
import js6team3.tbot.exception.ValidationException;
import js6team3.tbot.listener.TBotUpdatesListener;
import js6team3.tbot.repository.CatRepository;
import js6team3.tbot.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Сервис для работы с сущностью "Cat"
 *
 * @author Юрий Калынбаев
 */
@Service
@RequiredArgsConstructor
public class CatService {

    private static final Logger logger = LoggerFactory.getLogger(TBotUpdatesListener.class);

    private final CatRepository catRepository;

    /**
     * Получение полного списка экземпляров сущности "Cat"
     *
     * @return список экземпляров сущности "Cat"
     */
    public List<Cat> getAllCats() {
        return this.catRepository.findAll();
    }

    /**
     * Метод сохраняет в БД созданный объект питомца.
     *
     * @param cat экземпляр сущности "Cat"
     * @throws ValidationException
     * @see CatRepository
     */
    public Cat createCatInDB(Cat cat) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        if (cat.getNickname() == null || cat.getNickname().isBlank() || cat.getNickname().isEmpty()) {
            throw new CatNullParameterValueException("Кличка питомца не указана");
        }
        return catRepository.save(cat);
    }

    /**
     * Метод удаляет из БД питомца по идентификатору id
     *
     * @param id идентификатор питомца в БД
     * @see CatRepository
     */
    public Cat deleteCatById(Long id) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        Cat deleteCat = catRepository.findById(id).orElse(null);
        catRepository.deleteById(id);
        return deleteCat;
    }

    /**
     * Метод заменяет старые параметры питомца на те, что были переданы.
     *
     * @param id  идентификатор питомца в БД
     * @param cat экземпляр сущности "Cat"
     * @throws ValidationException
     * @see CatRepository
     * @see Cat
     */
    public Cat replaceCatById(Long id, Cat cat) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        Cat replaceCat = catRepository.findById(id).orElse(null);

        if (replaceCat != null) {
            replaceCat.setNickname(cat.getNickname());
            replaceCat.setBreed(cat.getBreed());
            replaceCat.setAge(cat.getAge());
            replaceCat.setDescription(cat.getDescription());
        } else {
            throw new CatNullParameterValueException("Недостаточно данных при попытке заменить данные у объекта Cat");
        }

        return catRepository.save(replaceCat);
    }
}

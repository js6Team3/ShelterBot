package js6team3.tbot.service;

import js6team3.tbot.entity.Cat;
import js6team3.tbot.exception.ValidationException;
import js6team3.tbot.listener.TBotUpdatesListener;
import js6team3.tbot.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
    private final ValidationService validationService;

    /**
     * Получение полного списка экземпляров сущности "Cat"
     *
     * @return список экземпляров сущности "Cat"
     */
    public Collection<Cat> getAllCats() {
        return this.catRepository.findAll();
    }

    /**
     * Метод возвращает из БД питомца по идентификатору id
     *
     * @param id идентификатор питомца в БД
     * @return getCat
     * @see CatRepository
     */
    public Cat getCatById(Long id) {
        Cat getCat = catRepository.findById(id).orElse(null);
        return getCat;
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
        if (!validationService.validate(cat)) {
            throw new ValidationException(cat.toString());
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
        if (!validationService.validate(replaceCat)) {
            throw new ValidationException(cat.toString());
        }

        replaceCat.setNickname(cat.getNickname());
        replaceCat.setBreed(cat.getBreed());
        replaceCat.setAge(cat.getAge());
        replaceCat.setDescription(cat.getDescription());

        return catRepository.save(replaceCat);
    }
}
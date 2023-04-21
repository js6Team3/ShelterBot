package js6team3.tbot.service;

import js6team3.tbot.entity.Cat;
import js6team3.tbot.entity.Dog;
import js6team3.tbot.exception.ValidationException;
import js6team3.tbot.listener.TBotUpdatesListener;
import js6team3.tbot.repository.CatRepository;
import js6team3.tbot.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Сервис для работы с сущностью "Dog"
 *
 * @author Юрий Калынбаев
 */
@Service
@RequiredArgsConstructor
public class DogService {

    private static final Logger logger = LoggerFactory.getLogger(TBotUpdatesListener.class);

    private final DogRepository dogRepository;
    private final ValidationService validationService;

    /**
     * Получение полного списка экземпляров сущности "Dog"
     *
     * @return список экземпляров сущности "Dog"
     */
    public Collection<Dog> getAllDogs() {
        return this.dogRepository.findAll();
    }

    /**
     * Метод возвращает из БД питомца по идентификатору id
     *
     * @param id идентификатор питомца в БД
     * @return getDog
     * @see DogRepository
     */
    public Dog getDogById(Long id) {
        Dog getDog = dogRepository.findById(id).orElse(null);
        return getDog;
    }

    /**
     * Метод сохраняет в БД созданный объект питомца.
     *
     * @param dog экземпляр сущности "Dog"
     * @throws ValidationException
     * @see DogRepository
     */
    public Dog createDogInDB(Dog dog) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        if (!validationService.validate(dog)) {
            throw new ValidationException(dog.toString());
        }
        return dogRepository.save(dog);
    }

    /**
     * Метод удаляет из БД питомца по идентификатору id
     *
     * @param id идентификатор питомца в БД
     * @see DogRepository
     */
    public Dog deleteDogById(Long id) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        Dog deleteDog = dogRepository.findById(id).orElse(null);
        dogRepository.deleteById(id);
        return deleteDog;
    }

    /**
     * Метод заменяет старые параметры питомца на те, что были переданы.
     *
     * @param id  идентификатор питомца в БД
     * @param dog экземпляр сущности "Dog"
     * @throws ValidationException
     * @see DogRepository
     * @see Dog
     */
    public Dog replaceDogById(Long id, Dog dog) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        Dog replaceDog = dogRepository.findById(id).orElse(null);
        if (!validationService.validate(replaceDog)) {
            throw new ValidationException(dog.toString());
        }

        replaceDog.setNickname(dog.getNickname());
        replaceDog.setBreed(dog.getBreed());
        replaceDog.setAge(dog.getAge());
        replaceDog.setDescription(dog.getDescription());

        return dogRepository.save(replaceDog);
    }
}

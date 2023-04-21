package js6team3.tbot.service;

import js6team3.tbot.entity.User;
import js6team3.tbot.exception.ValidationException;
import js6team3.tbot.listener.TBotUpdatesListener;
import js6team3.tbot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collection;

/**
 * Сервис для работы с сущностью "User"
 *
 * @author Юрий Калынбаев
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(TBotUpdatesListener.class);
    private final UserRepository userRepository;
    private final ValidationService validationService;

    /**
     * Получение полного списка экземпляров сущности "Cat"
     *
     * @return список экземпляров сущности "Cat"
     */
    public Collection<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Метод возвращает из БД пльзователя по идентификатору id
     *
     * @param id идентификатор пользователя в БД
     * @return getUser
     * @see UserRepository
     */
    public User getUserById(Long id) {
        User getUser = userRepository.findById(id).orElse(null);
        return getUser;
    }

    /**
     * Метод сохраняет в БД созданный объект пользователя
     *
     * @param user передает пользователя
     * @see UserRepository
     * @see User
     */
    public User createUserInDb(User user) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        if (!validationService.validate(user)) {
            throw new ValidationException(user.toString());
        }
        return userRepository.save(user);
    }

    /**
     * Метод удаляет из БД пользователя по идентификатору id
     *
     * @param id идентификатор пользователя в БД
     * @see UserRepository
     */
    public User deleteUserById(Long id) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        User deleteUser = userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);
        return deleteUser;
    }

    /**
     * Метод заменяет старые параметры пользователя на те, что были переданы.
     *
     * @param id   идентификатор пользователя в БД
     * @param user экземпляр сущности "User"
     * @throws ValidationException
     * @see UserRepository
     * @see User
     */
    public User replaceUserById(Long id, User user) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        User replaceUser = userRepository.findById(id).orElse(null);
        if (!validationService.validate(replaceUser)) {
            throw new ValidationException(user.toString());
        }

        replaceUser.setFirstName(user.getFirstName());
        replaceUser.setLastName(user.getLastName());
        replaceUser.setUserPhoneNumber(user.getUserPhoneNumber());
        replaceUser.setUserEmail(user.getUserEmail());

        return userRepository.save(replaceUser);
    }
}

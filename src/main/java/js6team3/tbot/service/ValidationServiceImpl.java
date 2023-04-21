package js6team3.tbot.service;

import js6team3.tbot.entity.Cat;
import js6team3.tbot.entity.Dog;
import js6team3.tbot.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(Cat cat) {

        return cat != null
                && cat.getNickname() != null
                && !StringUtils.isEmpty(cat.getNickname())
                && cat.getBreed() != null
                && !StringUtils.isEmpty(cat.getBreed())
                && cat.getAge() > 0
                && cat.getAge() < 15
                && cat.getDescription() != null
                && !StringUtils.isEmpty(cat.getDescription());
    }

    @Override
    public boolean validate(Dog dog) {

        return dog != null
                && dog.getNickname() != null
                && !StringUtils.isEmpty(dog.getNickname())
                && dog.getBreed() != null
                && !StringUtils.isEmpty(dog.getBreed())
                && dog.getAge() > 0
                && dog.getAge() < 30
                && dog.getDescription() != null
                && !StringUtils.isEmpty(dog.getDescription());
    }

    @Override
    public boolean validate(User user) {

        return user != null
                && user.getFirstName() != null
                && !StringUtils.isEmpty(user.getFirstName())
                && user.getLastName() != null
                && !StringUtils.isEmpty(user.getLastName())
                && user.getUserPhoneNumber() != null
                && !StringUtils.isEmpty(user.getUserPhoneNumber())
                && MatchingPhoneNumber(user.getUserPhoneNumber()) != null
                && !StringUtils.isEmpty(MatchingPhoneNumber(user.getUserPhoneNumber()))
                && user.getUserEmail() != null
                && !StringUtils.isEmpty(user.getUserEmail())
                && !validateEmail(user.getUserEmail());
    }

    /**
     * Метод проверяет правильность написания номера телефона.
     * Если номер указан неверно, метод возвращает null.
     * Если номер указан верно, он подгоняется под общий формат.
     *
     * @param telefone номер телефона пользователя
     * @see js6team3.tbot.repository.UserRepository
     */
    public String MatchingPhoneNumber(String telefone) {

        if (telefone.chars().filter(Character::isDigit).count() == 11) {
            String str = telefone.replaceAll("\\D+", "");
            String firstCharacter = str.substring(0, 1);
            if (firstCharacter.equals("8")) {
                return (str.charAt(0) + "(" + str.substring(1, 4) + ")" + str.substring(4, 7)
                        + "-" + str.substring(7, 9) + "-" + str.substring(9, 11));
            } else {
                return ("+" + str.charAt(0) + "(" + str.substring(1, 4) + ")" + str.substring(4, 7)
                        + "-" + str.substring(7, 9) + "-" + str.substring(9, 11));
            }
        } else {
            return null;
        }
    }

    /**
     * Метод проверяет правильность написания электронной почты пользователя.
     * Если почта указана неверно, метод возвращает false
     *
     * @param eMail электронная почта пользователя
     * @see js6team3.tbot.repository.UserRepository
     */
    public boolean validateEmail(String eMail) {

        if (eMail == null) {
            return false;
        } else {
            String regexPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
            return patternMatches(eMail, regexPattern);
        }
    }

    /**
     * Вспомогательный метод, соответствующий шаблону регулярных выражений
     *
     * @param TheStringBeingChecked строка которую нужно проверить
     * @param regexPattern          паттерн для проверки строки
     * @see java.util.regex.Pattern
     * @see java.util.regex.Matcher
     */
    public static boolean patternMatches(String TheStringBeingChecked, String regexPattern) {

        return Pattern.compile(regexPattern)
                .matcher(TheStringBeingChecked)
                .matches();
    }
}

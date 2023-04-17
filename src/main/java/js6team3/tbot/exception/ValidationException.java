package js6team3.tbot.exception;

/**
 * ошибка валидации
 * @author Юрий Калынбаев
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String entity) {
        super("Ошибка валидации сущности: " + entity);
    }
}

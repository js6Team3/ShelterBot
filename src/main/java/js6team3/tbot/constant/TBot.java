package js6team3.tbot.constant;

/**
 * The Bot menu
 * Constant: TBot
 *
 * @author Andrei Popov
 * (Типовые меню бота)
 */
public class TBot {
    // standard first greeting (Приветсвтие нового пользователя при подключении к боту)
    public static final String GREETING = """
            Бот приветствует нового пользователя, рассказывает о себе и просит пользователя выбрать приют:\s
            1 - Приют для кошек
            2 - Приют для собак
            0 - Позвать волонтера""";
    public static final String SELECT_MENU = """
            ShelterPets бот готов к работе:\s
            1 - Узнать информацию о приюте
            2 - Как взять животное из приюта
            3 - Прислать отчет о питомце
            0 - Позвать волонтера
             Жду комманды""";
}
package js6team3.tbot.telegram.constant;

/**
 * The Bot menu
 */
public class TBotConstant {
    /**
     * the standard first greeting
     */
    public static final String GREETING = """
            Бот приветствует нового пользователя, рассказывает о себе и просит пользователя выбрать приют:\s
            1 - Приют для кошек
            2 - Приют для собак
            0 - Позвать волонтера""";
    /**
     * select of the bot menu
     */
    public static final String SELECT_MENU = """
            ShelterPets бот готов к работе:\s
            1 - Узнать информацию о приюте
            2 - Как взять животное из приюта
            3 - Прислать отчет о питомце
            0 - Позвать волонтера
             Жду комманды""";
}
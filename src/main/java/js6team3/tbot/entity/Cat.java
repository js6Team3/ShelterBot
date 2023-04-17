package js6team3.tbot.entity;

import lombok.*;

/**
 * Сущность: Кот
 * @author Юрий Калынбаев
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cat {

    /** поле "id" - идентификатор экземпляра класса */
    private long id;

    /** поле "nickname" - кличка */
    private String nickname;

    /** поле "breed" - порода */
    private String breed;

    /** поле "age" - возраст */
    private int age;

    /** поле "description" - описание */
    private String description;
}

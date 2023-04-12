package js6team3.tbot.model;

import lombok.*;

/**
 * Сущность: Собака
 * @author Юрий Калынбаев
 * @version 1.0.0
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Dog {

    /** поле "id" - идентификатор экземпляра класса */
    private long id;

    /** поле "nickname" - кличка */
    private String nickname;

    /** поле "breed" - порода */
    private String breed;

    /** поле "yearOfBirth" - год рождения */
    private int yearOfBirth;

    /** поле "description" - описание */
    private String description;
}

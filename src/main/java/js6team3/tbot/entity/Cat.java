package js6team3.tbot.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Сущность: Cat
 *
 * @author Юрий Калынбаев
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cats")
public class Cat {

    /**
     * поле "id" - идентификатор экземпляра класса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    @Column(name = "id", nullable = false)
    private long id;

    /**
     * поле "nickname" - кличка
     */
    @Column(name = "nick_name")
    private String nickname;

    /**
     * поле "breed" - порода
     */
    @Column(name = "breed")
    private String breed;

    /**
     * поле "age" - возраст
     */
    @Column(name = "age")
    private int age;

    /**
     * поле "description" - описание
     */
    @Column(name = "description")
    private String description;

    /**
     * Связь "один ко многим"
     */
    @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    List<User> user;
}

package js6team3.tbot.entity.pet;

import js6team3.tbot.entity.User;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Entity: Dog
 *
 * @author Юрий Калынбаев
 * @author zalex14
 * @version 2.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dogs")
public class Dog {

    /**
     * The dog id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The dog nickname
     */
    @Column(name = "nick_name")
    private String nickname;

    /**
     * The dog's breed (порода)
     */
    @Column(name = "breed")
    private String breed;

    /**
     * The dog's age
     */
    @Column(name = "age")
    private int age;

    /**
     * The dog's feature and info
     */
    @Column(name = "description")
    private String description;
    /**
     * A link to the dog's user
     */
    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    List<User> user;

    public void setAge(int age) {
        if (age < 0) {                 // Проверка на отрицательное число
            age *= -1;                 // Инвентаризация числа
        } else if (age > 30) {         // Проверка на максимальный возраст
            age = 0;
        }
        this.age = age;
    }
}
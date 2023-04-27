package js6team3.tbot.entity.pet;

import js6team3.tbot.entity.User;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Entity: Cat
 *
 * @author Юрий Калынбаев
 * @author zalex14
 * @version 2.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cats")
public class Cat {

    /**
     * The cat's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The cat's nickname (кличка)
     */
    @Column(name = "nick_name")
    private String nickname;

    /**
     * The cat's breed (порода)
     */
    @Column(name = "breed")
    private String breed;

    /**
     * The cat's age
     */
    @Column(name = "age")
    private int age;

    /**
     * The cat;s features and info
     */
    @Column(name = "description")
    private String description;

    /**
     * A link to the cat's user
     */
    @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL, orphanRemoval = true)
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
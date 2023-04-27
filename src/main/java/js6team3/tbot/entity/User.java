package js6team3.tbot.entity;

import js6team3.tbot.entity.pet.Cat;
import js6team3.tbot.entity.pet.Dog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Entity: User
 *
 * @author Loginova Viktoria
 * @author zalex14
 * @version 2.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    /**
     * The user's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    @Column(name = "id")
    private Long id;

    /**
     * The user's name
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The user's surname
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The user's phone number
     */
    @Column(name = "user_phone_number")
    private String userPhoneNumber;

    /**
     * The user's email
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * A link to pet's id
     */
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;
}
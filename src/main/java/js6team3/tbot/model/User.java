package js6team3.tbot.model;
import lombok.*;
import javax.persistence.*;


/**
 * Entity: User
 * @author Loginova Viktoria
 * @version 1.0.0
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;                 // Identifier

    @Column(name = "first_name")
    private String firstName;       // Username

    @Column(name = "last_name")
    private String lastName;        //User last name

    @Column(name = "user_phone_number")
    private String userPhoneNumber; // User phone

    @Column(name = "user_email")
    private String userEmail;        // User e-mail

    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;
}

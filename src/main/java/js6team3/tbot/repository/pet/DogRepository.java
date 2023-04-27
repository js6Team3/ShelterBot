package js6team3.tbot.repository.pet;

import js6team3.tbot.entity.pet.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository: Dog
 * Implement report access layer for dogs
 *
 * @author Юрий Калынбаев
 * @author zalex14
 * @version 1.0
 */
@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
}
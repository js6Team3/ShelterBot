package js6team3.tbot.repository.pet;

import js6team3.tbot.entity.pet.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository: Cat
 * Implement report access layer for cats
 *
 * @author Юрий Калынбаев
 * @author zalex14
 * @version 1.0
 */
@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
}
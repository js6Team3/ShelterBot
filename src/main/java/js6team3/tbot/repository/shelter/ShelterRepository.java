package js6team3.tbot.repository.shelter;

import js6team3.tbot.entity.shelter.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository: Shelter
 * Implement report access layer for shelters
 *
 * @author zalex14
 * @version 1.0
 */
@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
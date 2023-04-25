package js6team3.tbot.repository.shelter;

import js6team3.tbot.entity.shelter.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implement report access layer for shelters
 *
 * @author zalex14
 */
@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
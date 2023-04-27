package js6team3.tbot.repository.shelter;

import js6team3.tbot.entity.shelter.Handler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository: Handler
 * Implement report access layer for recommended dog's handlers
 *
 * @author zalex14
 * @version 1.0
 */
@Repository
public interface HandlerRepository extends JpaRepository<Handler, Long> {
}
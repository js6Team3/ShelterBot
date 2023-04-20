package js6team3.tbot.repository;

import js6team3.tbot.entity.report.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implement data access layer
 *
 * @author Andrei Popov
 */
@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {
}
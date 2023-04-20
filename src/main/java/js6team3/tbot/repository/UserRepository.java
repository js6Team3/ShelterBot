package js6team3.tbot.repository;

import js6team3.tbot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andrei Popov
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
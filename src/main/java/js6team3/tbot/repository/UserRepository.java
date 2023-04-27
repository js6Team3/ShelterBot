package js6team3.tbot.repository;

import js6team3.tbot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository: User
 * Implement report access layer for users
 *
 * @author zalex14
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
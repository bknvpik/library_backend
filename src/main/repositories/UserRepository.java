package repositories;

import entities.Book;
import entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT * FROM user u WHERE u.username = ?1 LIMIT 1",
            nativeQuery = true)
    User findUserByUsername(String username);

    @Modifying
    @Transactional
    @Query (
            value = "INSERT INTO user (username, password, role) VALUES(?1, ?2, ?3)",
            nativeQuery = true
    )
    void addNewUser(String username, String password, String role);

    @Transactional
    @Modifying
    @Query (
            value = "DELETE FROM user u WHERE u.id = ?1",
            nativeQuery = true
    )
    void deleteUserById(Long bookId);
}

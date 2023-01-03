package repositories;

import entities.Book;
import entities.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {

    @Query(
            value = "SELECT * FROM user_book ub WHERE ub.user_id = ?1",
            nativeQuery = true
    )
    Iterable<UserBook> getUserBorrows(Long userId);

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO user_book (book_id, user_id, borrow_date, return_date) VALUES (?1, ?2, CURRENT_DATE, DATE(CURRENT_DATE + INTERVAL 2 MONTH))",
            nativeQuery = true)
    void borrowBookByUser(Long bookId, Long userId);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM user_book ub WHERE ub.book_id = ?1 AND ub.user_id = ?2",
            nativeQuery = true)
    void returnBookByUser(Long bookId, Long userId);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE user_book ub SET return_date = DATE_ADD(return_date, INTERVAL 2 MONTH) WHERE ub.book_id = ?1",
            nativeQuery = true)
    void longerReturnDate(Long bookId);
}

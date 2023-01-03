package repositories;

import entities.Book;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Fetch(FetchMode.JOIN)
    @Query(
            value = "SELECT * FROM book b LEFT JOIN user_book ub on b.id = ub.book_id where user_id <> ?1 OR user_id IS NULL",
            nativeQuery = true)
    Iterable<Book> getAllAvailableBooks(Long userId);

    @Fetch(FetchMode.JOIN)
    @Query(
            value = "SELECT * FROM book b INNER JOIN user_book ub on b.id = ub.book_id WHERE ub.user_id = ?1",
            nativeQuery = true)
    Iterable<Book> getUserBorrowedBooksList(Long userId);

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO book (title, author, year) VALUES (?1, ?2, ?3)",
            nativeQuery = true)
    void addNewBook(String title, String author, Integer year);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM book b WHERE b.id = ?1",
            nativeQuery = true)
    void deleteBookById(Long bookId);
}
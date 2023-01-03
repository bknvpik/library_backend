package services;

import entities.Book;
import entities.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.BookRepository;
import repositories.UserBookRepository;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserBookRepository userBookRepository;

    public Iterable<Book> getAllAvailableBooks(Long userId) {
        return bookRepository.getAllAvailableBooks(userId);
    }

    public Iterable<Book> getUserBorrowedBooksList(Long userId) {
        return bookRepository.getUserBorrowedBooksList(userId);
    }

    public void borrowBookByUser(Long bookId, Long userId) {
        userBookRepository.borrowBookByUser(bookId, userId);
    }
    public void returnBookByUser(Long bookId, Long userId) {
        userBookRepository.returnBookByUser(bookId, userId);
    }

    public void longerReturnDate(Long bookId) {
        userBookRepository.longerReturnDate(bookId);
    }

    public void addNewBook(Book book) {
        bookRepository.addNewBook(book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteBookById(bookId);
    }

    public Iterable<UserBook> getUserBorrows(Long userId) {
        return userBookRepository.getUserBorrows(userId);
    }
}

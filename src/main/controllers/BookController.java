package controllers;

import entities.User;
import exceptions.ApiRequestException;
import exceptions.UnauthorizedException;
import entities.Book;
import models.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import services.BookService;

import java.util.ArrayList;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books/{userId}")
    public Iterable<Book> getBooks(@PathVariable Long userId) throws UnauthorizedException {

        Iterable<Book> books;

        try {
            books = this.bookService.getAllAvailableBooks(userId);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot get books");
        }
        return books;
    }

    @GetMapping("/books/{userId}/borrowed")
    public Iterable<Book> getUserBorrowedBooksList(@PathVariable Long userId) throws UnauthorizedException {

        Iterable<Book> books;

        try {
            books = this.bookService.getUserBorrowedBooksList(userId);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot get books");
        }
        return books;
    }

    @GetMapping("/books/user/{userId}")
    public Iterable<entities.UserBook> getUserBorrows(@PathVariable Long userId) throws UnauthorizedException {

        Iterable<entities.UserBook> userBooks;

        try {
            userBooks = this.bookService.getUserBorrows(userId);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot get borrows");
        }
        return userBooks;
    }

    @PostMapping(path = "/book/{bookId}/user/{userId}/borrow")
    public String borrowBookByUser(@PathVariable Long bookId, @PathVariable Long userId) {

        try {
            this.bookService.borrowBookByUser(bookId, userId);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot borrow book");
        }
        return "Book borrowed!";
    }

    @DeleteMapping("/book/{bookId}/user/{userId}")
    public String returnBookByUser(@PathVariable Long bookId, @PathVariable Long userId) {

        try {
            this.bookService.returnBookByUser(bookId, userId);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot return book");
        }
        return "Book returned!";
    }

    @PostMapping(path = "/book/add", consumes = "application/json")
    public String addNewBook(@RequestBody() Book newBook) {
        try {
            this.bookService.addNewBook(newBook);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot add book");
        }
        return "Book added!";
    }

    @DeleteMapping("/book/{bookId}/delete")
    public String deleteBook(@PathVariable() Long bookId) throws ApiRequestException {

        Iterable<Book> deletedBook = null;

        try {
            this.bookService.deleteBook(bookId);
        }
        catch (Exception e) {
            throw new ApiRequestException("Cannot delete book with given id!");
        }
        return "Book deleted!";
    }

    @PutMapping("/book/{bookId}/longerDate")
    public String longerReturnDate(@PathVariable Long bookId) {

        try {
            bookService.longerReturnDate(bookId);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot longer return date");
        }
        return "Success!";
    }
}

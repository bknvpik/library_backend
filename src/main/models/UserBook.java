package models;

public class UserBook {
    protected Long bookId;
    protected Long userId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserBook() {}

    public UserBook(Long bookId, Long userId) {
        this.bookId = bookId;
        this.userId = userId;
    }
}

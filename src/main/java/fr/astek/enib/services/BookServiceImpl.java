package fr.astek.enib.services;

import fr.astek.enib.dao.BookDao;
import fr.astek.enib.exceptions.BookAlreadyExistsException;
import fr.astek.enib.exceptions.BookNotExistsException;
import fr.astek.enib.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public List<Book> getBook() {
        return bookDao.getBooks();
    }

    @Override
    public Book addBook(final Book book) throws BookAlreadyExistsException {
        if (null != book) {
            return bookDao.addBook(book);
        } else {
            return null;
        }
    }

    @Override
    public Book getBookById(final int idBook) throws BookNotExistsException {
        Optional<Book> bookById = bookDao.getBookById(idBook);
        if (bookById.isEmpty()) {
            throw new BookNotExistsException();
        }
        return bookById.get();
    }

    @Override
    public Set<Book> getBooksByAuthor(String author) {
        return bookDao.getBooksByAuthor(author);
    }

    @Override
    public Boolean deleteBook(int idBook) {
        return bookDao.deleteBook(idBook);
    }

    @Override
    public Book patchRating(int idBook, float rating) throws BookNotExistsException {
        Book book = bookDao.patchRating(idBook, rating);
        return checkBookExistence(book);
    }

    @Override
    public Book updateBook(int idBook, Book bookToUpdate) throws BookNotExistsException {
        Book book = bookDao.updateBook(idBook, bookToUpdate);
        return checkBookExistence(book);
    }

    @Override
    public List<Book> getFilteredBook(String title, String author, String genre, Float minRating) {
        List<Book> books = bookDao.getBooks();

        return books.stream()
                .filter(book -> title == null || book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(book -> author == null || book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .filter(book -> minRating == null || book.getRating() >= minRating)
                .filter(book -> genre == null || book.getGenre().stream()
                        .anyMatch(g -> g.equalsIgnoreCase(genre)))
                .collect(Collectors.toList());
    }

    private Book checkBookExistence(Book book) throws BookNotExistsException {
        if (book == null) {
            throw new BookNotExistsException();
        }
        return book;
    }

}

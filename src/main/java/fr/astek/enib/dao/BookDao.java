package fr.astek.enib.dao;

import fr.astek.enib.exceptions.BookAlreadyExistsException;
import fr.astek.enib.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookDao {
    List<Book> getBooks();

    Book addBook(Book book) throws BookAlreadyExistsException;

    Optional<Book> getBookById(int idBook);

    Set<Book> getBooksByAuthor(String owner);

    boolean deleteBook(int idBook);

    Book patchRating(int idBook, float rating);

    Book updateBook(int idBook, Book bookToUpdate);
}

package fr.astek.enib.services;

import fr.astek.enib.exceptions.BookAlreadyExistsException;
import fr.astek.enib.exceptions.BookNotExistsException;
import fr.astek.enib.model.Book;

import java.util.List;
import java.util.Set;

public interface BookService {
    List<Book> getBook();

    Book addBook(Book book) throws BookAlreadyExistsException;

    Book getBookById(int idBook) throws BookNotExistsException;

    Set<Book> getBooksByAuthor(String author);

    Boolean deleteBook(int idBook);

    Book patchRating(int idBook, float rating) throws BookNotExistsException;

    Book updateBook(int idBook, Book bookToUpdate) throws BookNotExistsException;

    List<Book> getFilteredBook(String title, String author, String genre, Float minRating);
}

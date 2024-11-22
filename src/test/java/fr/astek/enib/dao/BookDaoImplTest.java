package fr.astek.enib.dao;

import fr.astek.enib.exceptions.BookAlreadyExistsException;
import fr.astek.enib.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoImplTest {

    private BookDaoImpl bookDao;

    @BeforeEach
    void setUp() {
        bookDao = new BookDaoImpl();
    }

    @Test
    void testGetBooks() {
        List<Book> books = bookDao.getBooks();
        assertNotNull(books, "The list of books should not be null");
        assertFalse(books.isEmpty(), "The list of books should not be empty");
    }

    @Test
    void testAddBook() throws BookAlreadyExistsException {
        Book newBook = new Book(999, "New Title", "New Author", "description", new ArrayList<>(), new Date(), 4.5f, 0);
        bookDao.addBook(newBook);

        assertTrue(bookDao.getBooks().contains(newBook), "The new book should be added to the list");
    }

    @Test
    void testAddBookAlreadyExists() {
        Book existingBook = bookDao.getBooks().getFirst();
        assertThrows(BookAlreadyExistsException.class, () -> bookDao.addBook(existingBook),
                "Adding a duplicate book should throw BookAlreadyExistsException");
    }

    @Test
    void testGetBookById() {
        Book existingBook = bookDao.getBooks().getFirst();
        Optional<Book> retrievedBook = bookDao.getBookById(existingBook.getId());

        assertTrue(retrievedBook.isPresent(), "The book with the given ID should be found");
        assertEquals(existingBook, retrievedBook.get(), "The retrieved book should match the expected one");
    }

    @Test
    void testGetBooksByAuthor() {
        Book existingBook = bookDao.getBooks().getFirst();
        Set<Book> booksByAuthor = bookDao.getBooksByAuthor(existingBook.getAuthor());

        assertNotNull(booksByAuthor, "The set of books by author should not be null");
        assertFalse(booksByAuthor.isEmpty(), "The set of books by author should not be empty");
        assertTrue(booksByAuthor.contains(existingBook), "The set should contain the book by the specified author");
    }

    @Test
    void testDeleteBook() {
        Book existingBook = bookDao.getBooks().getFirst();
        boolean isDeleted = bookDao.deleteBook(existingBook.getId());

        assertTrue(isDeleted, "The book should be deleted successfully");
        assertFalse(bookDao.getBooks().contains(existingBook), "The deleted book should no longer be in the list");
    }

    @Test
    void testDeleteBookNonExistent() {
        boolean isDeleted = bookDao.deleteBook(9999);
        assertFalse(isDeleted, "Deleting a non-existent book should return false");
    }

    @Test
    void testPatchRating() {
        Book existingBook = bookDao.getBooks().getFirst();
        float newRating = 4.9f;

        Book updatedBook = bookDao.patchRating(existingBook.getId(), newRating);

        assertNotNull(updatedBook, "The updated book should not be null");
        assertEquals(newRating, updatedBook.getRating(), "The rating of the book should be updated");
    }

    @Test
    void testPatchRatingNonExistentBook() {
        Book updatedBook = bookDao.patchRating(9999, 4.5f);
        assertNull(updatedBook, "Patching a non-existent book should return null");
    }

    @Test
    void testUpdateBook() {
        Book existingBook = bookDao.getBooks().getFirst();
        Book updatedInfo = new Book(existingBook.getId(), "Updated Title", "Updated Author", "description", new ArrayList<>(), new Date(), 4.8f, 100);

        Book updatedBook = bookDao.updateBook(existingBook.getId(), updatedInfo);

        assertNotNull(updatedBook, "The updated book should not be null");
        assertEquals("Updated Title", updatedBook.getTitle(), "The title of the book should be updated");
        assertEquals("Updated Author", updatedBook.getAuthor(), "The author of the book should be updated");
    }

    @Test
    void testUpdateBookNonExistent() {
        Book updatedInfo = new Book(9999, "Updated Title", "Updated Author", "description", new ArrayList<>(), new Date(), 4.8f, 100);
        Book updatedBook = bookDao.updateBook(9999, updatedInfo);

        assertNull(updatedBook, "Updating a non-existent book should return null");
    }
}
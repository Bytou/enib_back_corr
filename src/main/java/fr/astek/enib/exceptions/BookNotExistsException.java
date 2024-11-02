package fr.astek.enib.exceptions;

import org.springframework.http.HttpStatus;

public class BookNotExistsException extends BookException {
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "Book does not exists in database";
    }
}

package fr.astek.enib.exceptions;

import org.springframework.http.HttpStatus;

public class BookAlreadyExistsException extends BookException {

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }

    @Override
    public String getMessage() {
        return "Book already exists in database";
    }
}

package ru.shasoka.dcis.prac_5.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.shasoka.dcis.prac_5.exceptions.create.ModelNotCreatedException;
import ru.shasoka.dcis.prac_5.exceptions.delete.ModelNotDeletedException;
import ru.shasoka.dcis.prac_5.exceptions.find.ModelNotFoundException;
import ru.shasoka.dcis.prac_5.exceptions.update.ModelNotUpdatedException;
import ru.shasoka.dcis.prac_5.exceptions.responses.ErrorResponse;


public class ErrorHandler<C extends ModelNotCreatedException, R extends ModelNotFoundException,
        U extends ModelNotUpdatedException, D extends ModelNotDeletedException> {

  @ExceptionHandler
  private ResponseEntity<ErrorResponse> handleCreateException(C e) {
    return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler
  private ResponseEntity<ErrorResponse> handleReadException(R e) {
    return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  private ResponseEntity<ErrorResponse> handleUpdateException(U e) {
    return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler
  private ResponseEntity<ErrorResponse> handleDeleteException(D e) {
    return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
  }

}

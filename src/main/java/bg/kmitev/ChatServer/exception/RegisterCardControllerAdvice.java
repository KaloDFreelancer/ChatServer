package bg.kmitev.ChatServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RegisterCardControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>("", HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<String> handleValidationException(ConstraintViolationException e) {
        return new ResponseEntity<>("", HttpStatus.PRECONDITION_FAILED);
    }
}

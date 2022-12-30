package br.com.pb.mspayment.framework.exception;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Date;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> argumentsNotValid
            (MethodArgumentNotValidException notValid) {

        BindingResult result = notValid.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder sb = new StringBuilder("Os campos seguintes não podem ser nulos");
        for (FieldError fieldError : fieldErrors) {
            sb.append(" | ");
            sb.append(" -> ");
            sb.append(fieldError.getField());
            sb.append(" <- ");
        }

        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), sb.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}



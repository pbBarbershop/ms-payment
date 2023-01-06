package br.com.pb.barbershop.mspayment.framework.exception;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PaymentExceptionHandlerTest {

    @InjectMocks
    private PaymentExceptionHandler exceptionHandler;

    @Test
    void handleExceptionInternal() {
        Exception ex = new Exception("test exception");
        WebRequest request = mock(WebRequest.class);

        PaymentExceptionHandler handler = new PaymentExceptionHandler();
        ResponseEntity<Object> response = handler.handleExceptionInternal(
                ex,
                null,
                null,
                HttpStatus.BAD_REQUEST,
                request
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals("test exception", errorResponse.getMessage());
    }

    //    @Test
    //    public void handleMethodArgumentNotValid() {
    //
    //        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
    //        when(ex.getBindingResult().getFieldError().getDefaultMessage()).thenReturn("campo inválido");
    //        WebRequest request = mock(WebRequest.class);
    //
    //        PaymentExceptionHandler handler = new PaymentExceptionHandler();
    //        ResponseEntity<Object> response = handler.handleMethodArgumentNotValid(ex, null, HttpStatus.BAD_REQUEST, request);
    //
    //        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    //        assertTrue(response.getBody() instanceof ErrorResponse);
    //        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
    //        assertEquals("campo inválido", errorResponse.getMessage());
    //    }

    @Test
    public void handle() {
        Exception exception = mock(Exception.class);
        GenericException genericException = mock(GenericException.class);
        when(genericException.getMessageDTO()).thenReturn("mensagem teste");
        when(genericException.getStatus()).thenReturn(HttpStatus.BAD_REQUEST);

        PaymentExceptionHandler handler = new PaymentExceptionHandler();
        ResponseEntity<Object> response = handler.handle(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), errorResponse.getMessage());

        response = handler.handle(genericException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        errorResponse = (ErrorResponse) response.getBody();
        assertEquals("mensagem teste", errorResponse.getMessage());
    }

    @Test
    public void handleDefault() {
        PaymentExceptionHandler handler = new PaymentExceptionHandler();
        ResponseEntity<Object> response = handler.handleDefault();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), errorResponse.getMessage());
    }

    @Test
    public void handleGenericException() {
        GenericException ex = mock(GenericException.class);
        when(ex.getMessageDTO()).thenReturn("mensagem teste");
        when(ex.getStatus()).thenReturn(HttpStatus.BAD_REQUEST);

        PaymentExceptionHandler handler = new PaymentExceptionHandler();
        ResponseEntity<Object> response = handler.handleGenericException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals("mensagem teste", errorResponse.getMessage());
    }
}

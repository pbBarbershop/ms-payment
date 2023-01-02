package br.com.pb.mspayment.framework.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class IdNotFoundException extends RuntimeException{

    public IdNotFoundException() {
        super(String.format("The requested id does not exist"));
    }
}

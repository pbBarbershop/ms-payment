package br.com.pb.mspayment.framework.exception;

public class DataIntegrityValidationException extends RuntimeException {

    public DataIntegrityValidationException(String message) {
        super(message);
    }
}
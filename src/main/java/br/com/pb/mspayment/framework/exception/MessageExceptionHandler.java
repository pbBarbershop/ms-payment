package br.com.pb.mspayment.framework.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class MessageExceptionHandler {

    private Date timestamp;
    private Integer status;
    private String message;
}

package br.com.pb.mspayment.domain.dto;

import br.com.pb.mspayment.domain.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {
    private Long id;
    private BigDecimal value;
    private String name;
    private String expiration;
    private String code;
    private Status status;
    private Long paymentFormId;

}

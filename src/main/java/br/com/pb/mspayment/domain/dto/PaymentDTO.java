package br.com.pb.mspayment.domain.dto;

import br.com.pb.mspayment.domain.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDTO {
    private BigDecimal value;
    private String customerName;
    private String paymentType;
    private LocalDateTime dateTime;
    private Status status;
}

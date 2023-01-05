package br.com.pb.barbershop.mspayment.domain.dto;

import br.com.pb.barbershop.mspayment.domain.model.Status;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {

    private BigDecimal value;
    private String customerName;
    private String paymentType;
    private Status status;
}

package br.com.pb.mspayment.domain.dto;

import br.com.pb.mspayment.domain.model.Status;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private BigDecimal value;

    private String customerName;

    private String paymentType;

    private LocalDateTime paymentDateTime;

    private Status status;

}

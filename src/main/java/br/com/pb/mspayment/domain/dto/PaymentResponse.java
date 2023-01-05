package br.com.pb.mspayment.domain.dto;

import br.com.pb.mspayment.domain.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

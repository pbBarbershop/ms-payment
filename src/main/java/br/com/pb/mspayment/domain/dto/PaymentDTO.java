package br.com.pb.mspayment.domain.dto;

import br.com.pb.mspayment.domain.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateTime;

    private Status status;
}

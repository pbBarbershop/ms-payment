package br.com.pb.mspayment.domain.dto;

import br.com.pb.mspayment.domain.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    @NotNull(message = "invalid field")
    @Positive(message = "value must be positive")
    private BigDecimal value;
    @NotBlank(message = "invalid field")
    @Pattern(regexp = "^([a-zA-ZãÃéÉíÍóÓêÊôÔáÁ\\s])+$", message = "field must contain letters only")
    private String customerName;
    @NotBlank(message = "invalid field")
    private String paymentType;

}

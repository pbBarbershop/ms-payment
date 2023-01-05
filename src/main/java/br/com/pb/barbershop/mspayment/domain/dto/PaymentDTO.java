package br.com.pb.barbershop.mspayment.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    @NotNull(message = "Campo inválido")
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal value;

    @NotBlank(message = "Campo inválido")
    @Pattern(regexp = "^([a-zA-ZãÃéÉíÍóÓêÊôÔáÁ\\s])+$", message = "Campo deve conter apenas letras")
    private String customerName;

    @NotBlank(message = "Campo inválido")
    private String paymentType;
}

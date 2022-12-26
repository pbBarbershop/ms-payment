package br.com.pb.mspayment.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive
    @Column(name = "valor")
    private BigDecimal value;
    @NotBlank
    @Size(max=100)
    @Column(name = "nome")
    private String name;
    @NotBlank
    @Size(max=7)
    @Column(name = "expiração")
    private String expiration;
    @NotBlank
    @Size(min=3, max=3)
    @Column(name = "código")
    private String code;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull
    @Column(name = "Forma de pagamento")
    private Long paymentFormId;

}

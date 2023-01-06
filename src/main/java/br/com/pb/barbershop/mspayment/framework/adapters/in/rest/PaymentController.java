package br.com.pb.barbershop.mspayment.framework.adapters.in.rest;

import br.com.pb.barbershop.mspayment.application.in.PaymentUseCase;
import br.com.pb.barbershop.mspayment.domain.dto.PageableDTO;
import br.com.pb.barbershop.mspayment.domain.dto.PaymentDTO;
import br.com.pb.barbershop.mspayment.domain.dto.PaymentResponse;
import br.com.pb.barbershop.mspayment.domain.model.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payment")
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentUseCase service;

    @Operation(summary = "Cadastrar pagamentos")
    @PostMapping
    public ResponseEntity<PaymentResponse> create(@RequestBody @Valid PaymentDTO paymentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(paymentDTO));
    }

    @Operation(summary = "Listar pagamentos")
    @GetMapping
    public ResponseEntity<PageableDTO> findAll(@RequestParam(required = false) Status status, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAll(status, pageable));
    }

    @Operation(summary = "Buscar pagamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Atualizar pagamento")
    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> update(
            @RequestBody @Valid PaymentDTO paymentDTO,
            @PathVariable @NotNull Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(paymentDTO, id));
    }

    @Operation(summary = "Atualizar status do pagamento para confirmado")
    @PutMapping("/{id}/confirm")
    public ResponseEntity confirmPayment(@PathVariable @NotNull Long id) {
        service.confirmPayment(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualizar status do pagamento para cancelado")
    @PutMapping("/{id}/cancel")
    public ResponseEntity cancelPayment(@PathVariable @NotNull Long id) {
        service.cancelPayment(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Excluir pagamento")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable @NotNull Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package br.com.pb.mspayment.framework.adapters;
import br.com.pb.mspayment.application.in.PaymentUseCase;
import br.com.pb.mspayment.domain.dto.PageableDTO;
import br.com.pb.mspayment.domain.dto.PaymentDTO;
import br.com.pb.mspayment.domain.model.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentUseCase service;

    @GetMapping
    public PageableDTO findAll(@RequestParam(required = false) Status status, Pageable pageable) {
        return this.service.findAll(status, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> update(@RequestBody @Valid PaymentDTO paymentDTO, @PathVariable @NotNull Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(paymentDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentDTO> delete(@PathVariable @NotNull Long id) {
        service.deletePayment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity confirmPayment(@PathVariable @NotNull Long id){
        service.confirmPayment(id);
        return ResponseEntity.ok().build();
    }
}




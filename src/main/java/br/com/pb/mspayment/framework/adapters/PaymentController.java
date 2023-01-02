package br.com.pb.mspayment.framework.adapters;

import br.com.pb.mspayment.application.in.PaymentUseCase;
import br.com.pb.mspayment.domain.dto.PageableDTO;
import br.com.pb.mspayment.domain.dto.PaymentDTO;
import br.com.pb.mspayment.domain.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<PaymentDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}




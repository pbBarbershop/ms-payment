package br.com.pb.mspayment.framework.adapters;

import br.com.pb.mspayment.application.in.PaymentService;
import br.com.pb.mspayment.domain.dto.PaymentDTO;
import br.com.pb.mspayment.domain.model.Payment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<Payment> createdPayment
            (@Valid @RequestBody Payment payment) throws Exception{
        Payment newpayment = service.createPayment(payment);
        return new ResponseEntity<>(newpayment, HttpStatus.CREATED);
    }

}




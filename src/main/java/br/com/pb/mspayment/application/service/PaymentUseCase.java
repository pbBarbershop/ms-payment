package br.com.pb.mspayment.application.service;

import br.com.pb.mspayment.application.in.PaymentService;
import br.com.pb.mspayment.application.out.PaymentRepository;
import br.com.pb.mspayment.domain.dto.PaymentDTO;
import br.com.pb.mspayment.domain.model.Payment;
import br.com.pb.mspayment.domain.model.Status;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentUseCase implements PaymentService {

    private final PaymentRepository repository;
    private final ModelMapper modelMapper;

    public Payment createPayment(Payment payment) {
        repository.save(payment);
        return modelMapper.map(payment, Payment.class);
    }


}

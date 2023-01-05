package br.com.pb.barbershop.mspayment.application.in;

import br.com.pb.barbershop.mspayment.domain.dto.PageableDTO;
import br.com.pb.barbershop.mspayment.domain.dto.PaymentDTO;
import br.com.pb.barbershop.mspayment.domain.dto.PaymentResponse;
import br.com.pb.barbershop.mspayment.domain.model.Status;
import org.springframework.data.domain.Pageable;

public interface PaymentUseCase {
    PageableDTO findAll(Status status, Pageable pageable);

    PaymentResponse findById(Long id);

    PaymentResponse update(PaymentDTO paymentDTO, Long id);

    void delete(Long id);

    PaymentResponse create(PaymentDTO dto);

    void confirmPayment(Long id);

    void cancelPayment(Long id);
}

package br.com.pb.mspayment.application.in;

import br.com.pb.mspayment.domain.dto.PageableDTO;
import br.com.pb.mspayment.domain.dto.PaymentDTO;
import br.com.pb.mspayment.domain.dto.PaymentResponse;
import br.com.pb.mspayment.domain.model.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public interface PaymentUseCase {

    PageableDTO findAll(Status status, Pageable pageable);

    PaymentResponse findById(Long id);

    PaymentResponse update(PaymentDTO paymentDTO, Long id);

    void deletePayment(Long id);

    PaymentResponse createPayment(PaymentDTO dto);

    void confirmPayment(Long id);

     void cancelPayment(Long id);
}




package br.com.pb.mspayment.application.in;


import br.com.pb.mspayment.domain.dto.PageableDTO;
import br.com.pb.mspayment.domain.dto.PaymentDTO;
import br.com.pb.mspayment.domain.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public interface PaymentUseCase {
    PageableDTO findAll(Status status, Pageable pageable);

    PaymentDTO findById(Long id);

    PaymentDTO update(PaymentDTO paymentDTO, Long id);
}




package br.com.pb.mspayment.application.in;


import br.com.pb.mspayment.domain.dto.PaymentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public interface PaymentUseCase {
    PaymentDTO getById(Long id);

    PaymentDTO createPayment(PaymentDTO dto);

    PaymentDTO updatePayment(Long id, PaymentDTO dto);

    void deletePayment(Long id);

    Page<PaymentDTO> findAll(Pageable paginacao);
}




package br.com.pb.mspayment.application.in;


import br.com.pb.mspayment.domain.dto.PaymentDTO;
import br.com.pb.mspayment.domain.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    Payment createPayment(Payment payment);

}




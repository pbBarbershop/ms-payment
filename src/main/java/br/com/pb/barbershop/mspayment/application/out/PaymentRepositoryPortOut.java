package br.com.pb.barbershop.mspayment.application.out;

import br.com.pb.barbershop.mspayment.domain.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


public interface PaymentRepositoryPortOut {
    Page findByStatus(Status status, Pageable pageable);
}

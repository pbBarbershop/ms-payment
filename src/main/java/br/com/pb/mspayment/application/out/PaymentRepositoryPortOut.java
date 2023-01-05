package br.com.pb.mspayment.application.out;

import br.com.pb.mspayment.domain.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepositoryPortOut {
    Page findByStatus(Status status, Pageable pageable);
}

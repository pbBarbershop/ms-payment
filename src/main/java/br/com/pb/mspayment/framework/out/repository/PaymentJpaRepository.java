package br.com.pb.mspayment.framework.out.repository;

import br.com.pb.mspayment.domain.model.Payment;
import br.com.pb.mspayment.domain.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
    Page findByStatus(Status status, Pageable pageable);
}

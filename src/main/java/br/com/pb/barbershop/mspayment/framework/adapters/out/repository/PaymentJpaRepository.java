package br.com.pb.barbershop.mspayment.framework.adapters.out.repository;

import br.com.pb.barbershop.mspayment.domain.model.Payment;
import br.com.pb.barbershop.mspayment.domain.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
    Page findByStatus(Status status, Pageable pageable);
}

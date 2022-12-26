package br.com.pb.mspayment.application.out;

import br.com.pb.mspayment.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

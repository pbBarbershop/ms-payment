package br.com.pb.barbershop.mspayment.framework.adapters.out.repository;

import br.com.pb.barbershop.mspayment.application.out.PaymentRepositoryPortOut;
import br.com.pb.barbershop.mspayment.domain.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryPortOut {

    private final PaymentJpaRepository repository;

    @Override
    public Page findByStatus(Status status, Pageable pageable) {
        return repository.findByStatus(status, pageable);
    }
}

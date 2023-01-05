package br.com.pb.mspayment.framework.out.repository;

import br.com.pb.mspayment.application.out.PaymentRepositoryPortOut;
import br.com.pb.mspayment.domain.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryPortOut {

    private final PaymentJpaRepository repository;

    @Override
    public Page findByStatus(Status status, Pageable pageable) {
        return repository.findByStatus(status, pageable);
    }
}

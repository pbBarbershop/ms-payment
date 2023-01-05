package br.com.pb.barbershop.mspayment.application.service;

import br.com.pb.barbershop.mspayment.application.in.PaymentUseCase;
import br.com.pb.barbershop.mspayment.domain.dto.PageableDTO;
import br.com.pb.barbershop.mspayment.domain.dto.PaymentDTO;
import br.com.pb.barbershop.mspayment.domain.dto.PaymentResponse;
import br.com.pb.barbershop.mspayment.domain.model.Payment;
import br.com.pb.barbershop.mspayment.domain.model.Status;
import br.com.pb.barbershop.mspayment.framework.adapters.out.repository.PaymentJpaRepository;
import br.com.pb.barbershop.mspayment.framework.exception.GenericException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase {

    private final PaymentJpaRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public PaymentResponse create(PaymentDTO dto) {
        Payment payment = modelMapper.map(dto, Payment.class);
        payment.setStatus(Status.PAYMENT_CREATED);
        repository.save(payment);

        return modelMapper.map(payment, PaymentResponse.class);
    }

    @Override
    public PageableDTO findAll(Status status, Pageable pageable) {
        Page page = status == null ? repository.findAll(pageable) : repository.findByStatus(status, pageable);

        List<PaymentResponse> payments = page.getContent();
        return PageableDTO
            .builder()
            .numberOfElements(page.getNumberOfElements())
            .totalElements(page.getTotalElements())
            .totalPages(page.getTotalPages())
            .paymentsList(payments)
            .build();
    }

    @Override
    public PaymentResponse findById(Long id) {
        Payment payment = repository
            .findById(id)
            .orElseThrow(() -> new GenericException(HttpStatus.BAD_REQUEST, "Id não encontrado!"));
        return modelMapper.map(payment, PaymentResponse.class);
    }

    @Override
    public PaymentResponse update(PaymentDTO paymentDTO, Long id) {
        Payment payment = repository
            .findById(id)
            .orElseThrow(() -> new GenericException(HttpStatus.BAD_REQUEST, "Id não encontrado!"));

        payment.setCustomerName(paymentDTO.getCustomerName());
        payment.setValue(paymentDTO.getValue());
        payment.setPaymentType(paymentDTO.getPaymentType());

        repository.save(payment);
        return modelMapper.map(payment, PaymentResponse.class);
    }

    @Override
    public void confirmPayment(Long id) {
        checkIfIdExists(id);
        Optional<Payment> payment = repository.findById(id);

        payment.get().setStatus(Status.PAYMENT_CONFIRMED);
        repository.save(payment.get());
    }

    @Override
    public void cancelPayment(Long id) {
        checkIfIdExists(id);
        Optional<Payment> payment = repository.findById(id);

        payment.get().setStatus(Status.PAYMENT_CANCELED);
        repository.save(payment.get());
    }

    @Override
    public void delete(Long id) {
        checkIfIdExists(id);
        repository.deleteById(id);
    }

    public void checkIfIdExists(Long id) {
        repository.findById(id).orElseThrow(() -> new GenericException(HttpStatus.BAD_REQUEST, "Id não encontrado!"));
    }
}

package br.com.pb.mspayment.application.service;

import br.com.pb.mspayment.application.in.PaymentUseCase;
import br.com.pb.mspayment.application.out.PaymentRepository;
import br.com.pb.mspayment.domain.dto.PageableDTO;
import br.com.pb.mspayment.domain.dto.PaymentDTO;
import br.com.pb.mspayment.domain.dto.PaymentResponse;
import br.com.pb.mspayment.domain.model.Payment;
import br.com.pb.mspayment.domain.model.Status;
import br.com.pb.mspayment.framework.exception.DataIntegrityValidationException;
import br.com.pb.mspayment.framework.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase {

    private final PaymentRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public PageableDTO findAll(Status status, Pageable pageable) {
        Page page = status == null ?
                repository.findAll(pageable) : repository.findByStatus(status, pageable);

        List<PaymentResponse> payments = page.getContent();
        return PageableDTO.builder().numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages()).paymentsList(payments).build();
    }

    public PaymentResponse findById(Long id) {
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        return modelMapper.map(payment, PaymentResponse.class);
    }

    @Override
    public PaymentResponse update(PaymentDTO paymentDTO, Long id) {

        Payment payment = repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));


        payment.setCustomerName(paymentDTO.getCustomerName());
        payment.setValue(paymentDTO.getValue());
        payment.setPaymentType(paymentDTO.getPaymentType());

        repository.save(payment);
        return modelMapper.map(payment, PaymentResponse.class);
    }

    @Override
    public void deletePayment(Long id) {
        checkIfIdExists(id);
        repository.deleteById(id);
    }
    @Override
    public PaymentResponse createPayment(PaymentDTO dto) {


        Payment payment = modelMapper.map(dto, Payment.class);
        payment.setStatus(Status.PAYMENT_CREATED);
        repository.save(payment);

        return modelMapper.map(payment, PaymentResponse.class);
    }

    public void confirmPayment(Long id) {
        checkIfIdExists(id);
        Optional<Payment> payment = repository.findById(id);

        payment.get().setStatus(Status.PAYMENT_CONFIRMED);
        repository.save(payment.get());
    }
    @Override
    public void cancelPayment(Long id){
        checkIfIdExists(id);
        Optional<Payment> payment = repository.findById(id);


        payment.get().setStatus(Status.PAYMENT_CANCELED);
        repository.save(payment.get());
    }

    private void checkIfIdExists(Long id) {
        repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

}


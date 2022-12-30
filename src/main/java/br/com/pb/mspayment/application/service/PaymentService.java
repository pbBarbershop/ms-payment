package br.com.pb.mspayment.application.service;

import br.com.pb.mspayment.application.in.PaymentUseCase;
import br.com.pb.mspayment.application.out.PaymentRepository;
import br.com.pb.mspayment.domain.dto.PageableDTO;
import br.com.pb.mspayment.domain.dto.PaymentDTO;
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

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase {

    private final PaymentRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public PageableDTO findAll(Status status, Pageable pageable) {
        Page page = status == null ?
                repository.findAll(pageable) : repository.findByStatus(status, pageable);
        List<PaymentDTO> payments = page.getContent();
        return PageableDTO.builder().numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages()).paymentsList(payments).build();
    }

    public PaymentDTO findById(Long id) {
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));
        return modelMapper.map(payment, PaymentDTO.class);
    }

    @Override
    public PaymentDTO update(PaymentDTO paymentDTO, Long id) {

        Payment payment = repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));

        if(LocalDateTime.now().isAfter(paymentDTO.getPaymentDateTime()))
            throw new DataIntegrityValidationException("Invalid date! retroative date is not allowed");

        payment.setCustomerName(paymentDTO.getCustomerName());
        payment.setValue(paymentDTO.getValue());
        payment.setPaymentDateTime(paymentDTO.getPaymentDateTime());
        payment.setPaymentType(paymentDTO.getPaymentType());

        repository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }

}
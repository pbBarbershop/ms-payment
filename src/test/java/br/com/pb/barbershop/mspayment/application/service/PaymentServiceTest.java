package br.com.pb.barbershop.mspayment.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import br.com.pb.barbershop.mspayment.domain.dto.PageableDTO;
import br.com.pb.barbershop.mspayment.domain.dto.PaymentDTO;
import br.com.pb.barbershop.mspayment.domain.dto.PaymentResponse;
import br.com.pb.barbershop.mspayment.domain.model.Payment;
import br.com.pb.barbershop.mspayment.domain.model.Status;
import br.com.pb.barbershop.mspayment.framework.adapters.out.repository.PaymentJpaRepository;
import br.com.pb.barbershop.mspayment.framework.exception.ErrorResponse;
import br.com.pb.barbershop.mspayment.framework.exception.GenericException;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService service;

    @Mock
    private PaymentJpaRepository repository;

    @Spy
    private ModelMapper mapper;

    private static final Long ID = 1L;

    @Test
    void shouldCreatePayment_whenValidPaymentDTO() {
        PaymentDTO paymentDTO = mock(PaymentDTO.class);
        Payment payment = mock(Payment.class);
        PaymentResponse paymentResponse = mock(PaymentResponse.class);

        when(mapper.map(paymentDTO, Payment.class)).thenReturn(payment);
        when(payment.getStatus()).thenReturn(Status.PAYMENT_CREATED);
        when(mapper.map(payment, PaymentResponse.class)).thenReturn(paymentResponse);

        PaymentResponse result = service.create(paymentDTO);

        assertEquals(paymentResponse, result);
    }

    @Test
    void shouldfindAll_whenStatusIsNull() {
        Pageable pageable = mock(Pageable.class);

        Page<Payment> page = mock(Page.class);
        when(page.getContent()).thenReturn(Arrays.asList(new Payment()));
        when(page.getNumberOfElements()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.getTotalPages()).thenReturn(1);

        when(repository.findAll(pageable)).thenReturn(page);

        PageableDTO result = service.findAll(null, pageable);

        assertEquals(1, result.getNumberOfElements());
        assertEquals(1L, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getPaymentsList().size());
    }

    @Test
    void shouldFindAll_whenStatusIsNotNull() {
        Status status = Status.PAYMENT_CREATED;
        Pageable pageable = mock(Pageable.class);

        Page<Payment> page = mock(Page.class);
        when(page.getContent()).thenReturn(Arrays.asList(new Payment()));
        when(page.getNumberOfElements()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.getTotalPages()).thenReturn(1);

        when(repository.findByStatus(status, pageable)).thenReturn(page);

        PageableDTO result = service.findAll(status, pageable);

        assertEquals(1, result.getNumberOfElements());
        assertEquals(1L, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getPaymentsList().size());
    }

    @Test
    void shouldFindPaymentById_AndReturn_Success() {
        Payment payment = new Payment();
        PaymentResponse paymentResponse = new PaymentResponse();

        when(repository.findById(anyLong())).thenReturn(Optional.of(payment));
        when(mapper.map(payment, PaymentResponse.class)).thenReturn(paymentResponse);

        PaymentResponse result = service.findById(1L);

        assertEquals(paymentResponse, result);
    }

    @Test
    void shouldFindById_AndReturn_IdNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GenericException.class, () -> service.findById(1L));
    }

    @Test
    void shouldUpdate_And_ReturnSuccess() {
        PaymentDTO paymentDTO = new PaymentDTO();
        Payment payment = new Payment();
        when(repository.findById(any())).thenReturn(Optional.of(payment));
        when(repository.save(any())).thenReturn(payment);

        PaymentResponse response = service.update(paymentDTO, ID);
        assertEquals(payment.getCustomerName(), response.getCustomerName());
        assertEquals(payment.getValue(), payment.getValue());
    }

    @Test
    void shouldConfirmPayment_And_ReturnSuccess() {
        Payment payment = new Payment();
        payment.setStatus(Status.PAYMENT_CREATED);
        when(repository.findById(ID)).thenReturn(Optional.of(payment));
        when(repository.save(payment)).thenReturn(payment);

        service.confirmPayment(ID);

        assertEquals(Status.PAYMENT_CONFIRMED, payment.getStatus());
    }

    @Test
    void shouldCancelPayment_And_ReturnSuccess() {
        Payment payment = new Payment();
        payment.setStatus(Status.PAYMENT_CREATED);
        when(repository.findById(ID)).thenReturn(Optional.of(payment));
        when(repository.save(payment)).thenReturn(payment);

        service.cancelPayment(ID);

        assertEquals(Status.PAYMENT_CANCELED, payment.getStatus());
    }

    @Test
    void shouldDelete_And_ReturnSuccess() {
        Payment payment = new Payment();
        when(repository.findById(ID)).thenReturn(Optional.of(payment));
        doNothing().when(repository).deleteById(ID);

        service.delete(ID);
        verify(repository).deleteById(ID);
    }

    @Test
    void shouldTryUpdateThen_ThrowException_WhenIdNotFound() {
        PaymentDTO paymentDTO = new PaymentDTO();
        when(repository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(GenericException.class, () -> service.update(paymentDTO, ID));
    }

    @Test
    void shouldThrowException_WhenIdNotFound() {
        when(repository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(GenericException.class, () -> service.checkIfIdExists(ID));
    }
}

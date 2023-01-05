package br.com.pb.mspayment.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import br.com.pb.mspayment.domain.dto.PaymentDTO;
import br.com.pb.mspayment.domain.dto.PaymentResponse;
import br.com.pb.mspayment.domain.model.Payment;
import br.com.pb.mspayment.framework.out.repository.PaymentJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

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
    public void whenUpdateShouldSucess() {
        PaymentDTO paymentDTO = new PaymentDTO();
        Payment payment = new Payment();
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(payment));
        Mockito.when(repository.save(any())).thenReturn(payment);

        PaymentResponse response = service.update(paymentDTO, ID);
        assertEquals(payment.getCustomerName(), response.getCustomerName());
        assertEquals(payment.getValue(), payment.getValue());
        verify(repository).save(any());
    }
}

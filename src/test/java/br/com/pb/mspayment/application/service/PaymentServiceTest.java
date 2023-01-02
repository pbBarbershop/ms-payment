package br.com.pb.mspayment.application.service;

import br.com.pb.mspayment.application.out.PaymentRepository;
import br.com.pb.mspayment.domain.dto.PageableDTO;
import br.com.pb.mspayment.domain.dto.PaymentDTO;
import br.com.pb.mspayment.domain.model.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Spy
    private ModelMapper modelMapper;

//    @Test
//    void shouldListAllThePayments() {
//        PaymentDTO payment = new PaymentDTO();
//        Page<PaymentDTO> page = new PageImpl<>(List.of(payment));
//        PageableDTO expectedPageableParameters = getPageableDTOParameters();
//
//        Mockito.when(paymentRepository.findAll((Pageable) any())).thenReturn(new PageImpl<Payment>(payment));
//
//        PageableDTO pageableParameters = paymentService.findAll(null, any(Pageable.class));
//
//        assertEquals(expectedPageableParameters, pageableParameters);
//    }
//
//    @Test
//    void shouldFindPaymentById() {
//        Payment payment = new Payment();
//        PaymentDTO response = new PaymentDTO();
//
//        Mockito.when(paymentRepository.findById(any())).thenReturn(Optional.of(payment));
//
//        PaymentDTO paymentDTO = paymentService.findById(1L);
//
//        assertEquals(response, paymentDTO);
//    }
//
//    private PageableDTO getPageableDTOParameters() {
//        return PageableDTO.builder().numberOfElements(1)
//                .totalElements(1L)
//                .totalPages(1)
//                .paymentsList(List.of(new PaymentDTO())).build();
//    }

}

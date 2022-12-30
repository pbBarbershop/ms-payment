package br.com.pb.mspayment.framework.adapters;

import br.com.pb.mspayment.application.service.PaymentService;
import br.com.pb.mspayment.domain.dto.PaymentDTO;
import br.com.pb.mspayment.domain.model.Payment;
import br.com.pb.mspayment.domain.model.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PaymentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class PaymentControllerTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    private static final String ID_URL = "/payment/1";

    private Payment payment;
    @MockBean
    private PaymentService paymentService;
    @Test
    public void updateTestShouldSucess() throws Exception {
        PaymentDTO paymentDTO = getPaymentDTO();
        when(paymentService.update(any(), any())).thenReturn(paymentDTO);
        String input = objectMapper.writeValueAsString(paymentDTO);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private PaymentDTO getPaymentDTO() {
        return PaymentDTO.builder().customerName("João")
                .paymentType("Cartão de Crédito")
                .value(new BigDecimal("1500"))
                .paymentDateTime(LocalDateTime.now())
                .status(Status.PAYMENT_CONFIRMED)
                .build();
    }


}

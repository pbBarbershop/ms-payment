package br.com.pb.barbershop.mspayment.framework.adapters.in.rest;

import br.com.pb.barbershop.mspayment.application.service.PaymentService;
import br.com.pb.barbershop.mspayment.domain.dto.PageableDTO;
import br.com.pb.barbershop.mspayment.domain.dto.PaymentDTO;
import br.com.pb.barbershop.mspayment.domain.dto.PaymentResponse;
import br.com.pb.barbershop.mspayment.domain.model.Payment;
import br.com.pb.barbershop.mspayment.domain.model.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PaymentController.class)
@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Spy
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentService paymentService;

    private static final String ID_URL = "/payment/1";
    private static final String URL = "/payment";

    private Payment payment;

    private PaymentResponse paymentResponse;

    @Test
    void create() throws Exception {
        PaymentDTO paymentDTO = getPaymentDTO();

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setValue(BigDecimal.valueOf(15.0));
        paymentResponse.setCustomerName("João");
        paymentResponse.setPaymentType("Cartão de Crédito");
        paymentResponse.setStatus(Status.PAYMENT_CREATED);

        when(paymentService.create(paymentDTO)).thenReturn(paymentResponse);
        String json = objectMapper.writeValueAsString(paymentDTO);

        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void findAll() throws Exception {
        PageableDTO pageableDTO = new PageableDTO();
        when(paymentService.findAll(any(), any())).thenReturn(pageableDTO);
        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get(URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void findById() throws Exception {
        PaymentDTO paymentDTO = new PaymentDTO();
        when(paymentService.findById(any())).thenReturn(paymentResponse);
        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get(ID_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void update() throws Exception {
        PaymentDTO paymentDTO = getPaymentDTO();
        PaymentResponse paymentResponse = new PaymentResponse();
        when(paymentService.update(any(), any())).thenReturn(paymentResponse);
        String json = objectMapper.writeValueAsString(paymentDTO);
        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(ID_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void confirmPayment() throws Exception {
        doNothing().when(paymentService).confirmPayment(1L);

        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(ID_URL + "/confirm")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void cancelPayment() throws Exception {
        doNothing().when(paymentService).cancelPayment(1L);

        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(ID_URL + "/cancel")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void delete() throws Exception {
        doNothing().when(paymentService).delete(1L);

        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete(ID_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    private PaymentDTO getPaymentDTO() {
        return PaymentDTO
                .builder()
                .customerName("João")
                .paymentType("Cartão de Crédito")
                .value(BigDecimal.valueOf(15.0))
                .build();
    }
}

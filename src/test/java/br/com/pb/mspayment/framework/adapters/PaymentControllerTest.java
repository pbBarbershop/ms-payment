package br.com.pb.mspayment.framework.adapters;

import br.com.pb.mspayment.application.service.PaymentService;
import br.com.pb.mspayment.domain.dto.PageableDTO;
import br.com.pb.mspayment.domain.dto.PaymentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PaymentController.class)
@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    private static final String URL = "/payment";
    private static final String ID_URL = "/payment/1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    void findAll() throws Exception {
        PageableDTO pageableDTO = new PageableDTO();
        when(paymentService.findAll(any(), any())).thenReturn(pageableDTO);
        MvcResult result = mockMvc.
                perform(MockMvcRequestBuilders.get(URL).
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON)).
                andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void findById() throws Exception {
        PaymentDTO paymentDTO = new PaymentDTO();
        when(paymentService.findById(any())).thenReturn(paymentDTO);
        MvcResult result = mockMvc.
                perform(MockMvcRequestBuilders.get(ID_URL).
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON)).
                andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
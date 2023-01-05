package br.com.pb.mspayment.domain.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableDTO {

    private Integer numberOfElements;
    private Long totalElements;
    private Integer totalPages;
    private List<PaymentResponse> paymentsList;
}

package br.com.pb.barbershop.mspayment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "MS Payment",
                description = "Microsserviço de pagamentos da barbearia",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8083/api/barbershop"
        )
)
public class MsPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPaymentApplication.class, args);
    }
}

package br.com.pb.barbershop.mspayment.framework.hekper.auth;

import br.com.pb.barbershop.mspayment.framework.exception.GenericException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtValidationInterceptor implements HandlerInterceptor {

    @Value("${token.secret}")
    private String jwtkey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        var jwt = request.getHeader("Authorization");
        if(jwt == null) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "null jwt");
        }
        jwt = jwt.replace("Bearer ", "");

        var decodedJwt = verify(jwt);
        request.setAttribute("decodedJwt", decodedJwt);
        return true;
    }

    private DecodedJWT verify(String jwt) {
        try {
            var decodedJwt = JWT.decode(jwt);
            return JWT
                    .require(selectAlgorithm(decodedJwt))
                    .build().verify(jwt);
        } catch (Exception e) {
            throw new GenericException(HttpStatus.UNAUTHORIZED, "Invalid token.");
        }
    }

    private Algorithm selectAlgorithm(DecodedJWT jwt) {
        if(jwt.getAlgorithm().equals("HS256")) return Algorithm.HMAC256(jwtkey);
        throw new GenericException(HttpStatus.UNAUTHORIZED, "Invalid algorithm.");
    }

}

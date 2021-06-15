package com.iuh.rencar_project.utils.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iuh.rencar_project.dto.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Trần Thế Duy
 * @version 0.1
 * @datetime May 1, 2021 4:03:39 PM
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    private final LocalDateTime now = LocalDateTime.now();

    private final ObjectMapper objectMapper;

    @Autowired
    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String message;
        ErrorResponse errorResponse;

        /*
         * Handle exception in request
         */
        final UnsupportedJwtException unsupportedJwtException = (UnsupportedJwtException) request
                .getAttribute("unsupportedJwtException");
        final SignatureException signatureException = (SignatureException) request.getAttribute("signatureException");
        final MalformedJwtException malformedJwtException = (MalformedJwtException) request
                .getAttribute("malformedJwtException");
        final ExpiredJwtException expiredJwtException = (ExpiredJwtException) request
                .getAttribute("expiredJwtException");
        final IllegalArgumentException exception = (IllegalArgumentException) request
                .getAttribute("illegalArgumentException");

        /*
         * If occur then response exception to FE with ErrorResponse
         */
        if (unsupportedJwtException != null) {
            message = "Unsupported Jwt Exception";
            errorResponse = new ErrorResponse(message, HttpStatus.UNAUTHORIZED, now);
            response.getWriter()
                    .write(this.writeBody(errorResponse, unsupportedJwtException, "Unsupported Jwt Error: "));
        } else if (signatureException != null) {
            message = "Signature Exception";
            errorResponse = new ErrorResponse(message, HttpStatus.UNAUTHORIZED, now);
            response.getWriter().write(this.writeBody(errorResponse, signatureException, "Signature Error: "));
        } else if (malformedJwtException != null) {
            message = "Malformed Jwt Exception";
            errorResponse = new ErrorResponse(message, HttpStatus.UNAUTHORIZED, now);
            response.getWriter().write(this.writeBody(errorResponse, malformedJwtException, "Malformed Jwt Error: "));
        } else if (expiredJwtException != null) {
            message = "Expired Jwt Exception";
            errorResponse = new ErrorResponse(message, HttpStatus.UNAUTHORIZED, now);
            response.getWriter().write(this.writeBody(errorResponse, expiredJwtException, "Expired Jwt Error: "));
        } else if (exception != null) {
            message = "Illegal Argument Exception";
            errorResponse = new ErrorResponse(message, HttpStatus.UNAUTHORIZED, now);
            response.getWriter().write(this.writeBody(errorResponse, exception, "Illegal Argument Error: "));
        } else {
            message = ((authException.getCause() != null) ? authException.getCause().toString() + " " : "") + authException.getMessage();
            errorResponse = new ErrorResponse(message, HttpStatus.UNAUTHORIZED, now);
            response.getWriter().write(this.writeBody(errorResponse, authException, "Auth Error: "));
        }
    }

    private String writeBody(ErrorResponse errorResponse, Throwable error, String nameError)
            throws JsonProcessingException {
        logger.error(nameError, error);
        return objectMapper.writeValueAsString(errorResponse);
    }
}

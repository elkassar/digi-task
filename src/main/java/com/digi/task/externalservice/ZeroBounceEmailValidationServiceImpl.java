package com.digi.task.externalservice;

import com.digi.task.exception.ExternalServiceDownBlockingException;
import com.digi.task.exception.ExternalServiceDownException;
import com.digi.task.exception.InvalidInputException;
import com.zerobounce.ZBValidateStatus;
import com.zerobounce.ZeroBounceSDK;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Data
@Service
public class ZeroBounceEmailValidationServiceImpl implements EmailValidationService {

    @Value("${app.zerobounce-api-key}")
    private String zeroBounceApiKey;

    @Override
    public void checkEmailValidity(String email) {
        log.info("Checking email validity using ZeroBounce external service");
        ZeroBounceSDK.getInstance().initialize(zeroBounceApiKey);
        ZeroBounceSDK.getInstance().validate(
                email,null,
                response -> {
                    if (!ZBValidateStatus.VALID.equals(response.getStatus())) {
                        throw new InvalidInputException(List.of("email"));
                    }
                },
                errorResponse -> {
                    throw new ExternalServiceDownBlockingException();
                });
    }
}

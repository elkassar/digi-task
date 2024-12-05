package com.digi.task.externalservice;

import com.digi.task.exception.ExternalServiceDownException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class EmailNotificationSendGridServiceImpl implements EmailNotificationService {

    @Async
    @Override
    public CompletableFuture<Void> sendEmailNotification(String email) {
        return CompletableFuture.runAsync(() -> {
            try {
                log.info("trying to send email notification!");
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new ExternalServiceDownException();
            }
            log.info("notificaton sent!");
        });
    }
}

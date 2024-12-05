package com.digi.task.externalservice;

import java.util.concurrent.CompletableFuture;

public interface EmailNotificationService {
    CompletableFuture<Void> sendEmailNotification(String email);
}

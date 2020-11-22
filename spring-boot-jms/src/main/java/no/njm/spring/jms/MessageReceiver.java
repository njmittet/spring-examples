package no.njm.spring.jms;

import static no.njm.spring.jms.Application.MESSAGES_QUEUE;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(MessageReceiver.class);

    private CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();

    @JmsListener(destination = MESSAGES_QUEUE)
    public void receiveMessage(Message message) {
        log.debug("Received: {}", message);
        completableFuture.complete(true);
    }

    CompletableFuture<Boolean> getCompletableFuture() {
        return completableFuture;
    }
}

package no.njm.spring.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
class MessageSender {

    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    void sendMessage() {
        Message message = new Message("receiver@mail.com", "Message body");
        log.debug("Sending: {}", message);
        jmsTemplate.convertAndSend(Application.MESSAGES_QUEUE, message);
    }
}

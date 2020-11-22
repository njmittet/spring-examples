package no.njm.spring.jms;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class Application {

    static final String MESSAGES_QUEUE = "spring-jms.private.messages";

    /**
     * The default implementation of the {@link MessageConverter}, the {link SimpleMessageConverter}, is only able
     * to convert basic types (such as String, Map, Serializable) and our {@link Message} is on purpose not
     * Serializable. We want to use Jackson to serialize the content to JSON. Spring Boot will detect the presence of
     * the bean and will associate it to both the default JmsTemplate and any JmsListenerContainerFactory.
     */
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        // http://bit.ly/2tdtJxl
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);

        // Get a reference to the message receiver bean and its CompletableFuture, allowing us to detect message
        // arrival.
        MessageReceiver messageReceiver = context.getBean(MessageReceiver.class);
        CompletableFuture<Boolean> completableFuture = messageReceiver.getCompletableFuture();

        // Configure sender and send message.
        MessageSender messageSender = context.getBean(MessageSender.class);
        messageSender.sendMessage();

        // Wait for message to arrive.
        completableFuture.get();

        // Exit the application by shutting down the Spring Context.
        context.close();
    }
}

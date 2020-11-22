package no.njm.spring.jms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class MessageTest {

    @Test
    public void testMessageWithDefaultConstructor() {
        // Setup
        Message message = new Message();

        // Verify
        assertNull(message.getTo());
        assertNull(message.getBody());
    }

    @Test
    public void testMessageWithValues() {
        // Setup
        Message message = new Message("to", "body");

        // Verify
        assertThat(message.getTo(), is("to"));
        assertThat(message.getBody(), is("body"));
    }
}

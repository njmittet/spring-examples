package no.njm.spring.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class GreetingTest {

    @Test
    public void testGreeting() {
        // Setup
        Greeting greeting = new Greeting(1L, "Greeting");

        // Verify
        assertThat(greeting.getId(), equalTo(1L));
        assertThat(greeting.getContent(), is("Greeting"));
    }
}

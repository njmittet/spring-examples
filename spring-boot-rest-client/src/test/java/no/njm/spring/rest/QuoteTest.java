package no.njm.spring.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class QuoteTest {

    @Test
    public void testQuote() {
        // Setup
        Value value = new Value();
        value.setId(1L);
        value.setQuote("TestQuote");

        Quote quote = new Quote();
        quote.setType("Test");
        quote.setValue(value);

        // Verify
        assertThat(quote.getType(), is("Test"));
        assertThat(quote.getValue(), equalTo(value));
    }
}

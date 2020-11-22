package no.njm.spring.cache;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

    @Test
    public void updatePrice() {
        // Setup
        Book book = new Book("000-0-00-000000-0", "Book", BigDecimal.TEN, LocalDateTime.now());

        // Act
        book.setPrice(BigDecimal.TEN.add(BigDecimal.ONE));

        // Verify
        assertThat(BigDecimal.valueOf(11.0), comparesEqualTo(book.getPrice()));
    }
}

package no.njm.spring.cache;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.common.base.MoreObjects;

public class Book {

    private final String isbn;
    private final String title;
    private BigDecimal price;
    private LocalDateTime lastUpdated;

    Book(String isbn, String title, BigDecimal price, LocalDateTime lastUpdated) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.lastUpdated = lastUpdated;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        lastUpdated = LocalDateTime.now();
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("isbn", isbn)
                .add("title", title)
                .add("price", price)
                .add("lastUpdated", lastUpdated.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .toString();
    }
}

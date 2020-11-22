package no.njm.spring.cache;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleBookRepository implements BookRepository {

    private final Map<String, Book> books = new HashMap<>();

    public SimpleBookRepository() {
        LocalDateTime lastUpdated = LocalDateTime.now();
        books.put("ISBN-001", new Book("ISBN-001", "First Book", BigDecimal.valueOf(101.50), lastUpdated));
        books.put("ISBN-002", new Book("ISBN-002", "Second Book", BigDecimal.valueOf(102.50), lastUpdated));
        books.put("ISBN-003", new Book("ISBN-003", "Third Book", BigDecimal.valueOf(103.50), lastUpdated));
    }

    @Override
    @Cacheable(value = "book")
    public Book getBook(String isbn) {
        simulateSlowService();
        // Avoiding returning a reference to the cached value.
        Book book = books.get(isbn);
        return new Book(book.getIsbn(), book.getTitle(), book.getPrice(), book.getLastUpdated());
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @CacheEvict(value = "book", allEntries = true)
    public void updateBookPrice(String isbn, BigDecimal price) {
        books.get(isbn).setPrice(price);
    }
}

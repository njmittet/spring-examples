package no.njm.spring.cache;

import java.math.BigDecimal;

public interface BookRepository {

    Book getBook(String isbn);

    void updateBookPrice(String isbn, BigDecimal price);
}

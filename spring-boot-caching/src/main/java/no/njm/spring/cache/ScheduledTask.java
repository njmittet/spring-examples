package no.njm.spring.cache;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    public static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private final BookRepository bookRepository;

    @Autowired
    public ScheduledTask(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Scheduled(fixedRate = 2000, initialDelay = 2000)
    public void fetchBook() {
        int randomNumber = new Random().nextInt(3) + 1;
        long start = System.nanoTime();
        Book book = bookRepository.getBook("ISBN-00" + randomNumber);
        long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        log.debug("Execution time: {} ms, Book: {}", millis, book);
    }

    @Scheduled(fixedRate = 15000, initialDelay = 15000)
    public void updateBook() {
        int number = new Random().nextInt(3) + 1;
        bookRepository.updateBookPrice("ISBN-00" + number, new BigDecimal("199.00"));
        log.debug("Updated book: ISBN-00{}", number);
    }
}

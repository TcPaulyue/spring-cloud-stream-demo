package io.pivotal.loansource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

@SpringBootApplication
@EnableBinding(LoanPublisher.class)
public class LoansourceApplication {

  private LoanPublisher loanPublisher;

  private static final Logger log = LoggerFactory.getLogger(LoansourceApplication.class);
  private List<String> names = Arrays.asList("Donald", "Theresa", "Vladimir", "Angela", "Emmanuel", "Shinzō", "Jacinda", "Kim");
  private List<Long> amounts = Arrays.asList(10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 100000000L);

  public static void main(String[] args) {
    SpringApplication.run(LoansourceApplication.class, args);
    log.info("The Loansource Application has started...");
  }





//  @Bean
//  public Supplier<Loan> supplyLoan() {
//    return () -> {
//      String rName = names.get(new Random().nextInt(names.size()));
//      Long rAmount = amounts.get(new Random().nextInt(amounts.size()));
//      Loan loan = new Loan(UUID.randomUUID().toString(), rName, rAmount);
//      log.info("{} {} for ${} for {}", loan.getStatus(), loan.getUuid(), loan.getAmount(), loan.getName());
//      return loan;
//    };
//  }
}

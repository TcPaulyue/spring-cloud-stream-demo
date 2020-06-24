package io.pivotal.loansource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.MimeTypeUtils;

import java.util.*;

public class LoanController extends QuartzJobBean{
    private MessageChannel messageChannel;

    private static final Logger log = LoggerFactory.getLogger(LoansourceApplication.class);
    private List<String> names = Arrays.asList("Donald", "Theresa", "Vladimir", "Angela", "Emmanuel", "Shinzō", "Jacinda", "Kim");
    private List<Long> amounts = Arrays.asList(10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 100000000L);
    private List<String> headers = Arrays.asList("loanA","loanB","loanC");

    public LoanController(LoanPublisher loanPublisher){
        messageChannel = loanPublisher.publish();
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String rName = names.get(new Random().nextInt(names.size()));
        Long rAmount = amounts.get(new Random().nextInt(amounts.size()));
        Loan loan = new Loan(UUID.randomUUID().toString(), rName, rAmount);
        String header = headers.get(new Random().nextInt(headers.size()));
        log.info("{} {} for ${} for {}  header={}", loan.getStatus(), loan.getUuid(), loan.getAmount(), loan.getName(),header);
        Message<Loan> msg = MessageBuilder.withPayload(loan)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE)
                .setHeader("my-header",header)
                .build();
        messageChannel.send(msg);
    }
}

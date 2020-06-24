package io.pivotal.loansource;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface LoanPublisher {

        @Output("output")
        MessageChannel publish();
}

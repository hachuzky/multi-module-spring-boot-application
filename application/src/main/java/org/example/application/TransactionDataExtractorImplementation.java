package org.example.application;

import org.example.filter.TransactionDataExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TransactionDataExtractorImplementation implements TransactionDataExtractor {
    private final static Logger LOG = LoggerFactory.getLogger(TransactionDataExtractorImplementation.class);

    @Override
    public String getText() {
        LOG.info("Hallo Welten");
        return "Hello World";
    }
}

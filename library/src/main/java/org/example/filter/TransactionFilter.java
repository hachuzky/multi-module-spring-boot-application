package org.example.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Component
@Order(1)
public class TransactionFilter implements WebFilter {
    private final static Logger LOG = LoggerFactory.getLogger(TransactionFilter.class);
    private static final String COUNT_START_TIME = "countStartTime";

    @Autowired
    private TransactionDataExtractor dataExtractor;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        exchange.getAttributes().put(COUNT_START_TIME, Instant.now().toEpochMilli());

        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    long startTime = exchange.getAttribute(COUNT_START_TIME);
                    long endTime = (Instant.now().toEpochMilli()) - startTime;
                    String routeUri = ((Route) exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR)).getUri().toASCIIString();
                    LOG.info("{} : {} ms - routed to {}. Message {}",
                            exchange.getRequest().getURI().getRawPath(),
                            endTime, routeUri, dataExtractor.getText());
                })
        );
    }
}

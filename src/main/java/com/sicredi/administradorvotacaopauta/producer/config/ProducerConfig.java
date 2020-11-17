package br.com.agibank.autoapprovalvalidatorbpm.producer.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Bean(name = "topicRabbitmq")
    public TopicExchange topicExchange() {
        return new TopicExchange(exchange);
    }

}

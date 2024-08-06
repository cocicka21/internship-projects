package by.artur.internship.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static by.artur.internship.utils.StringUtil.*;

@Configuration
public class MQConfiguration {

    @Bean
    public Queue myQueue() {
        return new Queue(MQ_QUEUE_NAME, false);
    }

    @Bean
    Exchange exchange() {
        return new TopicExchange(MQ_EXCHANGE, false, false);
    }

    @Bean
    Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(MQ_KEY).noargs();
    }

}

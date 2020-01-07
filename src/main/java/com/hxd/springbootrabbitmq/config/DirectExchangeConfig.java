package com.hxd.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Direct exchange
 *  Direct Exchange是RabbitMQ默认的交换机模式，也是最简单的模式，根据key全文匹配去寻找队列；
 *  消息中的路由键（routing key）如果和 Binding 中的 binding key 一致， 交换器就将消息发到对应的队列中。
 *  路由键与队列名完全匹配，它是完全匹配、单播的模式。
 *
 */
@Configuration
public class DirectExchangeConfig {

    @Bean
    public Queue directQueueA() {
        return new Queue("direct_queue_a");
    }

    @Bean
    public Queue directQueueB() {
        return new Queue("direct_queue_b");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct_exchange");
    }

    @Bean
    public Binding bindingDirectExchangeMessageA(Queue directQueueA,DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueA).to(directExchange).with("direct_routing_key_a");
    }

    @Bean
    public Binding bindingDirectExchangeMessageB(Queue directQueueB,DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueB).to(directExchange).with("direct_routing_key_b");
    }

}

package com.hxd.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Direct exchange
 *
 *  Direct exchange根据Routing key完全匹配发送消息。其中两个要素：
 *   1.将routing key的queue与exchange绑定；
 *   2.如果新queue中的routing key与之前绑定的routing key（bingBing key）相等，exchange则将路由至新的queue中；
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

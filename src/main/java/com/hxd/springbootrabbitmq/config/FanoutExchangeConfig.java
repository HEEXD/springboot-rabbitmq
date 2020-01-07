package com.hxd.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout Exchange
 *
 * Fanout 就是我们熟悉的广播模式或者订阅模式，给 Fanout 交换机发送消息，绑定了这个交换机的所有队列都收到这个消息；
 * 所有发送到该Exchange的消息路由到所有与它绑定的Queue中，忽略routing key。Fanout exchange是广播路由的最佳选择。
 */
//@Configuration
public class FanoutExchangeConfig {

    @Bean
    public Queue fanoutQueueA() {
        return new Queue("fanout_queue_a");
    }

    @Bean
    public Queue fanoutQueueB() {
        return new Queue("fanout_queue_b");
    }

    @Bean
    public Queue fanoutQueueC() {
        return new Queue("fanout_queue_c");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_exchange");
    }

    @Bean
    public Binding bindingExchangeMessageA(Queue fanoutQueueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeMessageB(Queue fanoutQueueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueB).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeMessageC(Queue fanoutQueueC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueC).to(fanoutExchange);
    }
}

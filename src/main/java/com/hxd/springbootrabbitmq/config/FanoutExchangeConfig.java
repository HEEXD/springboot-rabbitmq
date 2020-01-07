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
    public Queue messageA() {
        return new Queue("messageA");
    }

    @Bean
    public Queue messageB() {
        return new Queue("messageB");
    }

    @Bean
    public Queue messageC() {
        return new Queue("messageC");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_exchange");
    }

    @Bean
    public Binding bindingExchangeMessageA(Queue messageA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(messageA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeMessageB(Queue messageB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(messageB).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeMessageC(Queue messageC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(messageC).to(fanoutExchange);
    }
}

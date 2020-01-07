package com.hxd.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TopicExchange
 *
 * Topic 是 RabbitMQ 中最灵活的一种方式，可以根据 routing_key 自由的绑定不同的队列；
 * Topic exchange将消息路由到一个或者多个Queue，如果说Direct是精确匹配的话，那Topic就是模糊匹配，可以通过通配符满足一部分规则就可以传送；
 */
@Configuration
public class TopicExchangeConfig {

    /**
     * 创建queue
     */
    @Bean
    public Queue topicQueueA() {
        return new Queue("topic_queue_a");
    }

    /**
     * 创建queue
     */
    @Bean
    public Queue topicQueueB() {
        return new Queue("topic_queue_b");
    }

    /**
     * 创建交换机
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topic_exchange");
    }

    /**
     * 根据绑定规则将队列绑定到相应的交换机上（bindingKey）
     */
    @Bean
    public Binding bindingExchangeMessageA(Queue topicQueueA, TopicExchange exchange) {
        return BindingBuilder.bind(topicQueueA).to(exchange).with("topic.message");//routingKey,匹配规则
    }

    /**
     * 将队列"queueMessageB"绑定到交换机上，匹配规则是 topic.#
     */
    @Bean
    public Binding bindingExchangeMessageB(Queue topicQueueB,TopicExchange exchange) {
        //符号"#"匹配一个或多个词，符号"*"只能匹配一个词；
        //因此"topic.#"能够匹配到"topic.test.demo"，但是"topic.*"只会匹配到"topic.test"
        return BindingBuilder.bind(topicQueueB).to(exchange).with("topic.#");
    }
}

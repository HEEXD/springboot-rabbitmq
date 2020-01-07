package com.hxd.springbootrabbitmq;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * 原生连接方式测试
 */
class RabbitmqTests {

    private final static String QUEUE_NAME = "queueName";

    /**
     * 原生连接方式
     */
    static Connection getRabbitConnection() {
        ConnectionFactory connectionFactory = new ConnectionFactory();// 创建连接管理器对象
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456");
        connectionFactory.setHost("192.168.1.142");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("my_vhost");
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();// 创建连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 消息提供方
     */
    @Test
    void testSender() {
        try {
            Connection connection = getRabbitConnection();
            Channel channel = connection.createChannel();// 创建通道
            // 声明队列【参数说明：参数一：队列名称，参数二：是否持久化；参数三：是否独占模式；参数四：消费者断开连接时是否删除队列；参数五：消息其他参数】
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "HelloRabbit!";
            // 发送内容【参数说明：参数一：交换机名称；参数二：队列名称，参数三：消息的其他属性-routing headers，此属性为MessageProperties.PERSISTENT_TEXT_PLAIN用于设置纯文本消息存储到硬盘；参数四：消息主体】
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("已发送消息：" + message);
            // 关闭连接
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息接收方
     */
    static void testReceiver() {
        try {
            Connection connection = getRabbitConnection();
            Channel channel = connection.createChannel();// 创建通道
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 创建订阅器，并接受消息
            channel.basicConsume(QUEUE_NAME, false, "", new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");// 消息正文
                    System.out.println("消费消息："+message);
                    channel.basicAck(envelope.getDeliveryTag(), false); // 手动确认消息【参数说明：参数一：该消息的index；参数二：是否批量应答，true批量确认小于index的消息】
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testReceiver();
    }

}

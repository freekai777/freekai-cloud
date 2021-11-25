package com.freekai.cloud.config;

import com.freekai.cloud.producer.MqMsgProducer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq的配置
 *
 * x-message-ttl 发送到队列的消息在丢弃之前可以存活多长时间（毫秒）。
 * x-expires 队列在被自动删除（毫秒）之前可以使用多长时间。
 * x-max-length 队列在开始从头部删除之前可以包含多少就绪消息。
 * x-max-length-bytes 队列在开始从头部删除之前可以包含的就绪消息的总体大小。
 * x-dead-letter-exchange 设置队列溢出行为。这决定了在达到队列的最大长度时消息会发生什么。有效值为drop-head或reject-publish。交换的可选名称，如果消息被拒绝或过期，将重新发布这些名称。
 * x-dead-letter-routing-key 可选的替换路由密钥，用于在消息以字母为单位时使用。如果未设置，将使用消息的原始路由密钥。
 * x-max-priority 队列支持的最大优先级数;如果未设置，队列将不支持消息优先级。
 * x-queue-mode 将队列设置为延迟模式，在磁盘上保留尽可能多的消息以减少内存使用;如果未设置，队列将保留内存缓存以尽快传递消息。
 * x-queue-master-locator 将队列设置为主位置模式，确定在节点集群上声明时队列主机所在的规则。
 */
@Configuration
public class RabbitMqConfig {

    @Autowired
    RabbitMqConfigProperties configProperties;

    @Autowired
    RabbitAdmin rabbitAdmin;

    public static final String QUEUE_A = "queueA";

    public static final String QUEUE_A_ROUTING_KEY = "routing.key.queueA";

    public static final String QUEUE_B = "queueB";

    public static final String QUEUE_B_ROUTING_KEY = "routing.key.queueB";

    public static final String DLQ_QUEUE = "dlq.queue"; // 死信队列名
    public static final String QUEUE_DLX_ROUTING_KEY = "routing.key.*";

    public static final String DLX_EXCHANGE = "freekai-rabbit-dlx-exchange";
    public static final String EXCHANGE = "freekai-rabbit-exchange";

    /**
     * 此处设置“原型bean”， 是因为每次都需要有回调，如果是单利，则最后一次的confirmCallBack会覆盖掉之前的
     * @param connectionFactory
     * @return
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new MqMsgProducer());
        rabbitTemplate.setReturnCallback(new MqMsgProducer());
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
    //创建交换机和对列
    @Bean
    public void createExchangeQueue (){
        rabbitAdmin.declareExchange(defaultExchange());
        rabbitAdmin.declareExchange(defaultDlxExchange());
        rabbitAdmin.declareQueue(queueA());
        rabbitAdmin.declareQueue(queueB());
        rabbitAdmin.declareQueue(dlqQueue());
    }


    /**
     * 声明一个连接
     */

    @Bean
    public ConnectionFactory connectionFactory() throws IOException {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(configProperties.getHost(), configProperties.getPort());
        connectionFactory.setUsername(configProperties.getUsername());
        connectionFactory.setPassword(configProperties.getPassword());
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        return connectionFactory;
    }

    /**
     * 声明一个交换机
     */
    @Bean
    public DirectExchange defaultExchange(){
        return new DirectExchange(EXCHANGE);
    }

    // 死信交换机 (直连交换机 不支持 通配符，只能精确匹配路由key， 这里换成Topic的试下(#匹配的是n（n>=1）多个词，*表示一个单词, 故路由key取名 xx.yy这种形式))
    @Bean
    public TopicExchange defaultDlxExchange(){
        return new TopicExchange(DLX_EXCHANGE);
    }

    @Bean
    public Queue queueA(){

        // 参数配置
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("x-dead-letter-exchange", DLX_EXCHANGE); // 死信交换机
        argMap.put("x-dead-letter-routing-key", QUEUE_A_ROUTING_KEY); // 死信路由键
        /**
         * Caused by: com.rabbitmq.client.ShutdownSignalException: channel error; protocol method:
         * #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - inequivalent arg 'x-message-ttl' for queue 'queueA' in vhost '/':
         * received the value '60000' of type 'signedint' but current is none, class-id=50, method-id=10)
         */
        argMap.put("x-message-ttl",60000); // 消息多少毫秒后过期， 可以理解为消息可以在当前队列待多长时间
        Queue queue = new Queue(QUEUE_A, true, false, false,argMap);
        return queue;
    }

    /**
     * 声明 队列B, 80s 后，消息传入死信队列
     * @return
     */
    @Bean
    public Queue queueB(){
        // 参数配置
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("x-dead-letter-exchange", DLX_EXCHANGE); // 死信交换机
        argMap.put("x-dead-letter-routing-key", QUEUE_B_ROUTING_KEY); // 死信路由键
        argMap.put("x-message-ttl",80000); // 消息多少毫秒后过期， 可以理解为消息可以在当前队列待多长时间
        Queue queue = new Queue(QUEUE_B, true, false, false,argMap);
        return queue;
    }


    /**
     * 死信队列
     * @return
     */
    @Bean
    public Queue dlqQueue(){
        Queue queue = new Queue(DLQ_QUEUE, true);
        return queue;
    }

    /**
     * 绑定死信队列
     *
     */
    @Bean
    public Binding bindDlqQueue(){
       return BindingBuilder.bind(dlqQueue()).to(defaultDlxExchange()).with(QUEUE_DLX_ROUTING_KEY);
    }

    /**
     * 绑定队列
     */
    @Bean
    public Binding bindQueueA(){
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(QUEUE_A_ROUTING_KEY);
    }
    /**
     * 绑定队列
     */
    @Bean
    public Binding bindQueueB(){
        return BindingBuilder.bind(queueB()).to(defaultExchange()).with(QUEUE_B_ROUTING_KEY);
    }

}

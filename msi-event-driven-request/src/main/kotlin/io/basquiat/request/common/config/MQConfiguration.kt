package io.basquiat.request.common.config

import io.basquiat.request.common.property.RabbitMQProperties
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * rabbit mq queue container configuration
 * created by basquiat
 */
@Configuration
class MQConfiguration(
    private val rabbitmq: RabbitMQProperties,
) {

    /** default rabbit mq setting */

    @Bean
    fun connectionFactory(): CachingConnectionFactory {
        val connectionFactory = CachingConnectionFactory(rabbitmq.host, rabbitmq.port)
        connectionFactory.username = rabbitmq.username
        connectionFactory.setPassword(rabbitmq.password)
        connectionFactory.virtualHost = rabbitmq.virtualHost
        return connectionFactory
    }

    @Bean
    fun rabbitMQTemplate(): RabbitTemplate {
        return RabbitTemplate(connectionFactory())
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(rabbitmq.exchange)
    }

    /** db queue setting */
    @Bean
    fun dbQueue(): Queue {
        return Queue(rabbitmq.dbQueue)
    }

    @Bean
    fun dbQueueBinding(): Binding {
        return BindingBuilder.bind(dbQueue())
                             .to(exchange())
                             .with(rabbitmq.dbRoutingKey)
    }

    /** log queue setting */
    @Bean
    fun logQueue(): Queue {
        return Queue(rabbitmq.logQueue)
    }

    @Bean
    fun logQueueBinding(): Binding {
        return BindingBuilder.bind(logQueue())
                             .to(exchange())
                             .with(rabbitmq.logRoutingKey)
    }

}
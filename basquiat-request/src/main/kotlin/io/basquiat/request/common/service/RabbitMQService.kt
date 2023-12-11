package io.basquiat.request.common.service

import io.basquiat.request.common.property.RabbitMQProperties
import io.basquiat.request.common.util.logger
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

/**
 * rabbit mq service
 *
 * queue 에 대응하는 메소드를 제공한다.
 *
 * created by basquiat
 *
 */
@Service
class RabbitMQService(
    private val rabbitmq: RabbitMQProperties,
    private val rabbitTemplate: RabbitTemplate,
) {

    private val log = logger<RabbitMQService>()

    fun sendDbQueue(message: String) {
        rabbitmq.run {
            rabbitTemplate.convertAndSend(exchange, dbRoutingKey, message)
        }
    }

    fun sendLogQueue(message: String) {
        rabbitmq.run {
            rabbitTemplate.convertAndSend(exchange, logRoutingKey, message)
        }
    }

    @RabbitListener(queues = ["db-chk"])
    fun receiveDbMessage(message: String) {
        log.info("Received db message: $message")
    }

    @RabbitListener(queues = ["log-message"])
    fun receiveLogMessage(message: String) {
        log.info("Received log message: $message")
    }

}
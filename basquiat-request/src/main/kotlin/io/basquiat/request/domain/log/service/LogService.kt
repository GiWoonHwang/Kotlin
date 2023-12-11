package io.basquiat.request.domain.log.service

import io.basquiat.request.common.property.RabbitMQProperties
import io.basquiat.request.common.util.logger
import org.springframework.stereotype.Service

@Service
class LogService(
    private val rabbitMQProperties: RabbitMQProperties,
) {

    private val log = logger<LogService>()

    fun logProcess(code: String) {
        log.info(code)
    }

}
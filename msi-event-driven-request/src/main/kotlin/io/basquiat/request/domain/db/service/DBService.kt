package io.basquiat.request.domain.db.service

import io.basquiat.request.common.property.RabbitMQProperties
import io.basquiat.request.common.util.logger
import org.springframework.stereotype.Service

@Service
class DBService(
    private val rabbitMQProperties: RabbitMQProperties,
) {

    private val log = logger<DBService>()

    fun dbProcess(code: String) {
        log.info(code)
    }

}
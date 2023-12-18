package io.basquiat.request.api.usecase.db

import io.basquiat.request.common.service.RabbitMQService
import io.basquiat.request.domain.db.service.DBService
import org.springframework.stereotype.Service

@Service
class DBUseCase(
    private val dbService: DBService,
    private val message: RabbitMQService,
) {
    fun execute(code: String) {
        dbService.dbProcess(code)
        message.sendDbQueue("db service $code")
    }
}
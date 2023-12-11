package io.basquiat.request.api.usecase.log

import io.basquiat.request.common.service.RabbitMQService
import io.basquiat.request.domain.log.service.LogService
import org.springframework.stereotype.Service

@Service
class LogUseCase(
    private val logService: LogService,
    private val message: RabbitMQService,
) {

    fun execute(code: String) {
        try {
            logService.logProcess(code)
        } catch (e: Exception) {
            // 공통 객체가
            //message.sendLogQueue("log service $code")
            /** db에 에러 로그를 쌓는 로직 */


        }
    }

}
package io.basquiat.request.api.controller

import io.basquiat.request.api.usecase.log.LogUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/log")
class LogController(
    private val logUseCase: LogUseCase,
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{code}")
    fun db(@PathVariable("code") code: String): String {
        logUseCase.execute(code)
        return "OK"
    }

}

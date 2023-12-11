package io.basquiat.request.api.controller

import io.basquiat.request.api.usecase.db.DBUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/db")
class DBController(
    private val dbUseCase: DBUseCase,
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{code}")
    fun db(@PathVariable("code") code: String): String {
        dbUseCase.execute(code)
        return "OK"
    }

}

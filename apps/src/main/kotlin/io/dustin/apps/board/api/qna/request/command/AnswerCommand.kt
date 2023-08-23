package io.dustin.apps.board.api.qna.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AnswerCommand(

    @field:NotNull(message = "adminId는 필수값 입니다.")
    @Schema(description = "답글을 작성자의  adminId", example = "1")
    @field:Min(1, message = "adminId는 필수입니다. 최소값은 1입니다.")
    val adminId: Long,

    @Schema(description = "질문객체 id 값", example = "1")
    @field:Min(1, message = "questionId는 필수입니다. 최소값은 1입니다.")
    val questionId: Long,

    @Schema(description = "질문 내용", example = "어쩌구 저쩌구 ~")
    @field:NotNull(message = "content는 필수입니다.")
    @field:NotBlank(message = "content는 필수입니다.")
    val content: String,



)
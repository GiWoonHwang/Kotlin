package io.dustin.apps.board.api.qna.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "질문 수정 요청 객체")
data class QuestionModifyCommand(

    @Schema(description = "유저 고유 id 값", example = "1")
    @field:Min(1, message = "userId는 필수입니다. 최소값은 1입니다.")
    val userId: Long,

    @Schema(description = "질문객체 id 값", example = "1")
    @field:Min(1, message = "questionId는 필수입니다. 최소값은 1입니다.")
    val questionId: Long,

    @Schema(description = "질문 제목", example = "문의드립니다.")
    @field:NotNull(message = "subject는 필수값 입니다.")
    @field:NotBlank(message = "발행관리기관계좌번호 필수입니다.")
    val subject: String,

    @Schema(description = "질문 내용", example = "어쩌구 저쩌구 ~")
    @field:NotNull(message = "content는 필수입니다.")
    @field:NotBlank(message = "content는 필수입니다.")
    val content: String,


    )
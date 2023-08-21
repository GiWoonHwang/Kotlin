package io.dustin.apps.board.api.usecase.qna.question

import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto
import io.dustin.apps.board.domain.qna.question.service.ReadQuestionService
import io.dustin.apps.board.domain.qna.question.service.WriteQuestionService
import io.dustin.apps.common.model.response.ResultResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteQuestionUseCase (
    private val writeQuestionService: WriteQuestionService,
) {

    @Transactional
    fun execute(userId: Long, subject: String, content: String): ResultResponse<QuestionDto> {
        val question: Question = writeQuestionService.create(userId, subject, content)
        val result =  QuestionDto.from(question)
        return ResultResponse.of(result)
    }
}

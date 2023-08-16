package io.dustin.apps.board.api.community

import io.dustin.apps.board.api.community.request.command.PostingCreateCommand
import io.dustin.apps.board.api.community.request.command.PostingUpdateCommand
import io.dustin.apps.board.api.community.request.query.AllPostingQuery
import io.dustin.apps.board.api.community.request.query.PostingDetailQuery
import io.dustin.apps.board.api.usecase.community.posting.DeletePostingUseCase
import io.dustin.apps.board.api.usecase.community.posting.ModifyPostingUseCase
import io.dustin.apps.board.api.usecase.community.posting.ReadPostingUseCase
import io.dustin.apps.board.api.usecase.community.posting.WritePostingUseCase
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDetailDto
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.board.domain.community.posting.model.dto.PostingListDto
import io.dustin.apps.common.code.CommonMessage
import io.dustin.apps.common.model.response.CommonResponse
import io.dustin.apps.common.model.response.ResultResponse
import io.dustin.apps.common.model.response.ResultResponsePagination
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/posting")
@Tag(name = "게시물 관련 API", description = "게시물 작성,수정,삭제,조회 API 를 제공한다.")
class PostingController (

    private val readPostingUseCase: ReadPostingUseCase,
    private val writePostingUseCase: WritePostingUseCase,
    private val modifyPostingUseCase: ModifyPostingUseCase,
    private val deletePostingUseCase: DeletePostingUseCase

) {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/all")
    @Operation(
        summary = "게시물 관련 API",
        description = "작성된 모든 게시물을 가져온다."
    )
    fun allPostings(@RequestBody @Valid query: AllPostingQuery): ResultResponsePagination<PostingListDto> {
        return readPostingUseCase.allPostingList(query)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/detail")
    @Operation(
        summary = "게시물 관련 API",
        description = "특정 게시물을 가져온다."
    )
    fun postingDetail(@RequestBody @Valid query: PostingDetailQuery): ResultResponse<PostingDetailDto> {
        return readPostingUseCase.postingDetail(query)
    }

    @PostMapping("/create")
    fun createPosting(@RequestBody @Valid command: PostingCreateCommand): CommonResponse {
        writePostingUseCase.execute(command.userId, command.subject, command.content)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }

    @PatchMapping("/{postingId}")
    fun modifyPosting(
        @PathVariable("postingId") postingId: Long,
        @RequestBody @Valid command: PostingUpdateCommand,
    ): PostingDto {
        command.checkAssignment()
        return modifyPostingUseCase.execute(command.userId, postingId, command.subject, command.content)
    }

    @DeleteMapping("/{postingId}/user/{userId}")
    fun deletePosting(
        @PathVariable("postingId") postingId: Long,
        @PathVariable("userId") userId: Long,
    ): PostingDto {
        /** req 데이터 검증로직 추가 필요  */
        return deletePostingUseCase.execute(userId, postingId)
    }
}

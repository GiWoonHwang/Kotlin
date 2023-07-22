package io.dustin.apps.board.domain.community.comment.repository.custom

import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto

interface CustomCommentRepository {
    fun commentListByPosting(userId: Long, postingId: Long, size: Int, nextId: Long?): List<CommentDto>
    fun replyListByComment(userId: Long, commentId: Long, size: Int, nextId: Long?): List<CommentDto>
}
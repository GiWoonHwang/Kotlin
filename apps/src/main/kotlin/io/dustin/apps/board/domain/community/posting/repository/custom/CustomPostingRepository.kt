package io.dustin.apps.board.domain.community.posting.repository.custom

import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.board.domain.community.posting.model.dto.PostingListDto

interface CustomPostingRepository {
    fun getPosting(userId: Long, postingId: Long): PostingDto
    fun getPostingList(userId: Long, nextId: Long?, size: Int): List<PostingListDto>
    fun getMyPosting(userId: Long, postingId: Long): PostingDto
    fun getMyPostingList(userId: Long, nextId: Long, size: Int): List<PostingListDto>
}

package io.dustin.apps.board.domain.bookmark.service

import io.dustin.apps.board.domain.bookmark.repository.BookmarkRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadBookmarkService (
    private val bookmarkRepository: BookmarkRepository

) {
    @Transactional(readOnly = true)
    fun getBookmark(userId: Long, boardId: Long) =
            bookmarkRepository.findByUserIdAndBoardId(userId, boardId)

}

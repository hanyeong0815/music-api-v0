package com.self.music.service;

import com.self.music.domain.Board;
import com.self.music.dto.response.BoardListResponse.BoardListRes;
import com.self.music.dto.response.BoardResponse;
import org.springframework.data.domain.PageRequest;

public interface BoardService {
    boolean insertNewBoard(Board toEntity);

    BoardResponse findById(String boardId);

    BoardListRes findAllPagination(PageRequest pageable);
}

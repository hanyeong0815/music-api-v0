package com.self.music.service;

import com.self.music.domain.Board;
import com.self.music.dto.response.BoardListResponse.BoardListRes;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BoardService {
    boolean insertNewBoard(Board toEntity);

    List<Board> testFindAllBoard();

    BoardListRes findAllPagination(PageRequest pageable);
}

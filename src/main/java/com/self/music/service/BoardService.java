package com.self.music.service;

import com.self.music.domain.Board;
import com.self.music.dto.request.BoardUpload.BoardUploadRequest;

import java.util.List;

public interface BoardService {
    boolean insertNewBoard(Board toEntity);

    List<Board> testFindAllBoard();
}

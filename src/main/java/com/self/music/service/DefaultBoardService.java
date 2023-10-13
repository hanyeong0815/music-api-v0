package com.self.music.service;

import com.self.music.domain.Board;
import com.self.music.domain.BoardRepo;
import com.self.music.dto.response.BoardListResponse.BoardListRes;
import com.self.music.dto.response.BoardListResponse.BoardRes;
import com.self.music.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultBoardService implements BoardService{
    private final BoardRepo boardRepo;

    @Override
    public boolean insertNewBoard(Board toEntity) {
        boardRepo.save(toEntity);
        return true;
    }

    @Override
    public BoardResponse findById(String boardId) {
        Board board = boardRepo.findById(boardId).orElseThrow();
        return new BoardResponse(board.getId(), board.getUserId(), board.getTitle(), board.getLyrics(), board.getMusicUrl(), board.getImgUrl(), board.getUploadDate());
    }

    @Override
    public BoardListRes findAllPagination(Pageable pageable) {
        Page<BoardRes> boardPage = boardRepo.findAllBy(pageable);
        return new BoardListRes(boardPage.getContent(), boardPage.getTotalPages(), boardPage.getTotalElements());
    }
}

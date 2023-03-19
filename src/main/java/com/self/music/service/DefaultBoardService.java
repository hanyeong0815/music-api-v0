package com.self.music.service;

import com.self.music.domain.Board;
import com.self.music.domain.BoardRepo;
import com.self.music.dto.response.BoardListResponse.BoardListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultBoardService implements BoardService{
    private final BoardRepo boardRepo;

    @Override
    public boolean insertNewBoard(Board toEntity) {
        return boardRepo.save(toEntity) != null;
    }

    @Override
    public List<Board> testFindAllBoard() {
        return boardRepo.findAll();
    }

    @Override
    public BoardListRes findAllPagination(PageRequest pageable) {
        Page<Board> boardPage = boardRepo.findAll(pageable);
        BoardListRes res = new BoardListRes(boardPage.getContent(), boardPage.getTotalPages(), boardPage.getTotalElements());
        return res;
    }
}

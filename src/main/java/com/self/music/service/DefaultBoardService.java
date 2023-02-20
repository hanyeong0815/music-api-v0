package com.self.music.service;

import com.self.music.domain.Board;
import com.self.music.domain.BoardRepo;
import lombok.RequiredArgsConstructor;
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
}

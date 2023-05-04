package com.self.music.service;

import com.self.music.domain.Board;
import com.self.music.domain.BoardRepo;
import com.self.music.dto.response.BoardListResponse.BoardListRes;
import com.self.music.dto.response.BoardListResponse.BoardRes;
import com.self.music.dto.response.BoardResponse;
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
        boardRepo.save(toEntity);
        return true;
    }

    @Override
    public List<Board> testFindAllBoard() {
        return boardRepo.findAll();
    }

    @Override
    public BoardResponse findById(String boardId) {
        Board board = boardRepo.findById(boardId).orElseThrow();
        BoardResponse boardRes = new BoardResponse(board.getId(), board.getUserId(), board.getTitle(), board.getLyrics(), board.getMusicUrl(), board.getImgUrl(), board.getUploadDate());
        return boardRes;
    }

    @Override
    public BoardListRes findAllPagination(PageRequest pageable) {
        Page<BoardRes> boardPage = boardRepo.findAllBy(pageable);
//        List<BoardRes> boardResList = boardPage.stream().map((board) -> {
//            BoardRes boardRes = BoardRes.builder()
//                    .boardId(board.getId())
//                    .userId(board.getUserId())
//                    .userName(board.getUserName())
//                    .title(board.getTitle())
//                    .uploadData(board.getUploadData())
//                    .uploadIp(board.getUploadIp())
//                    .imgUrl(board.getImgUrl())
//                    .build();
//            return boardRes;
//        }).collect(Collectors.toList());
        BoardListRes res = new BoardListRes(boardPage.getContent(), boardPage.getTotalPages(), boardPage.getTotalElements());
        return res;
    }
}

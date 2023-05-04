package com.self.music.domain;

import com.self.music.dto.response.BoardListResponse.BoardRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepo extends MongoRepository<Board, String> {
    Page<BoardRes> findAllBy(PageRequest pageable);
}

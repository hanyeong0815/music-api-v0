package com.self.music.domain;

import com.self.music.dto.response.BoardListResponse.BoardRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface BoardRepo extends MongoRepository<Board, String> {
    Page<BoardRes> findAllBy(Pageable pageable);

//    @Query("{'uploadDate': { '$lte' : ?0}}")
//    List<Board> findByUploadDateBetween(Instant six);
}
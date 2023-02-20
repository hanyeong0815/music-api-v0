package com.self.music.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepo extends MongoRepository<Board, String> {
}

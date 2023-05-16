package com.self.music.service;

import com.self.music.dto.request.TokenReissueRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> reissue(TokenReissueRequest req);
}

package com.self.music.service;

import com.self.music.dto.request.TokenReissueRequest;
import com.self.music.dto.response.JwtResponse;

public interface AuthenticationService {
    JwtResponse reissue(TokenReissueRequest req);
}

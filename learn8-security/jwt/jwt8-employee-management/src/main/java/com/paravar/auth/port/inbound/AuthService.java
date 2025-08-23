package com.paravar.auth.port.inbound;

import com.paravar.adapter.web.auth.LoginRequest;

public interface AuthService {

    String login(LoginRequest request);
}

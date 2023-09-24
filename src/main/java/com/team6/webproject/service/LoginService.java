package com.team6.webproject.service;

import com.team6.webproject.controller.login.LoginRequest;
import com.team6.webproject.exception.ApplicationException;
import com.team6.webproject.mapper.UserMapper;
import com.team6.webproject.dto.add.CreateUserRequest;
import com.team6.webproject.repository.user.User;
import com.team6.webproject.repository.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class LoginService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void createUser(CreateUserRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            log.error("Username taken");
            throw new ApplicationException("Username taken error.");
        } else {
            User user = userMapper.toUser(request);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
            log.info("Finished service createUser, created user = " + request.getUsername());
        }
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            Map<String, Object> claims = new HashMap<>();

            claims.put("id", user.getId());
            claims.put("username", user.getUsername());

            long time = System.currentTimeMillis();
            long lifespan = 7200000;

            byte[] keyBytes = Decoders.BASE64.decode("bXlzZWNyZXRpc3NlY3VyZWZvcm5vd2F0bGVhc3Rmb3Jub3c=");

            Key key = Keys.hmacShaKeyFor(keyBytes);
            log.info("Finished login service.");

            return Jwts.builder()
                    .addClaims(claims)
                    .setIssuedAt(new Date(time))
                    .setExpiration(new Date(time + lifespan))
                    .signWith(key).compact();
        } else {
            log.error("Wrong username or password");
            throw new ApplicationException("Wrong username or password");
        }
    }
}

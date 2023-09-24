package com.team6.webproject.mapper;

import com.team6.webproject.dto.add.CreateUserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperImplTest {

    private final UserMapperImpl mapper = new UserMapperImpl();

    @Test
    void toUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("username");

        assertEquals("username", mapper.toUser(request).getUsername());
        assertNull(mapper.toUser(null));
    }
}
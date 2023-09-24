package com.team6.webproject.mapper;

import com.team6.webproject.dto.add.CreateUserRequest;
import com.team6.webproject.repository.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(source = "username", target = "username")

    User toUser(CreateUserRequest user);

}

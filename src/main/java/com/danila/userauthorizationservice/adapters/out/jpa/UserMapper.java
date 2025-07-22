package com.danila.userauthorizationservice.adapters.out.jpa;

import com.danila.userauthorizationservice.domain.model.User;
import com.danila.userauthorizationservice.infrastructure.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}

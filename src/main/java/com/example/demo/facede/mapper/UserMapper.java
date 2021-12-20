package com.example.demo.facede.mapper;

import com.example.demo.domain.User;
import com.example.demo.facede.dto.AdminUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

// todo, this is just an example
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<AdminUserDTO, User> {

    AdminUserDTO toDto(User user);
}

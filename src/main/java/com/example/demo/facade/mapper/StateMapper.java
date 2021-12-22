package com.example.demo.facade.mapper;

import com.example.demo.domain.State;
import com.example.demo.facade.dto.StateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface StateMapper extends EntityMapper<StateDTO, State>  {
}

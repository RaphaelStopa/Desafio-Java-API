package com.example.demo.facade.mapper;

import com.example.demo.domain.Process;
import com.example.demo.facade.dto.ProcessDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PoliticalIdMapper.class, StateMapper.class})
public interface ProcessMapper extends EntityMapper<ProcessDTO, Process>  {
}

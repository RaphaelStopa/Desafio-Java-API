package com.example.demo.facade.mapper;

import com.example.demo.domain.Process;
import com.example.demo.facade.dto.ProcessDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProcessMapper extends EntityMapper<ProcessDTO, Process>  {
}

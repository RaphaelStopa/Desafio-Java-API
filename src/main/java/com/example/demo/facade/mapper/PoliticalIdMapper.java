package com.example.demo.facade.mapper;

import com.example.demo.domain.Political;
import com.example.demo.facade.dto.PoliticalDTO;
import com.example.demo.facade.dto.PoliticalIdDTO;
import com.example.demo.facade.dto.PoliticalToUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PoliticalPartyMapper.class})
public interface PoliticalIdMapper extends EntityMapper<PoliticalIdDTO, Political>  {

}

package com.example.demo.facade.mapper;

import com.example.demo.domain.PoliticalParty;
import com.example.demo.facade.dto.PoliticalPartyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PoliticalPartyMapper extends EntityMapper<PoliticalPartyDTO, PoliticalParty>  {
}

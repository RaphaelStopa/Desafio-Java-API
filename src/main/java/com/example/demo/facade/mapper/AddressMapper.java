package com.example.demo.facade.mapper;

import com.example.demo.domain.Address;
import com.example.demo.facade.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {CityMapper.class, PoliticalIdMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper extends EntityMapper<AddressDTO, Address>  {
}

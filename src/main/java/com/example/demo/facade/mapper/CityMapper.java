package com.example.demo.facade.mapper;

import com.example.demo.domain.City;
import com.example.demo.facade.dto.CityDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CityMapper extends EntityMapper<CityDTO, City>  {
}

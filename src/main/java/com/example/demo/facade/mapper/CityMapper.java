package com.example.demo.facade.mapper;

import com.example.demo.domain.City;
import com.example.demo.domain.Political;
import com.example.demo.facade.dto.CityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Mapper(componentModel = "spring", uses = {StateMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper extends EntityMapper<CityDTO, City>  {


}

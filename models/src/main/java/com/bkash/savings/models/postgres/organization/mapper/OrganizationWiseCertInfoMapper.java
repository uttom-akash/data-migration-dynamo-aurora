package com.bkash.savings.models.postgres.organization.mapper;

import com.bkash.savings.models.postgres.organization.OrganizationEntity;
import com.bkash.savings.models.postgres.organization.dto.OrganizationWiseCertInfoDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrganizationWiseCertInfoMapper {

    OrganizationWiseCertInfoMapper MAPPER = Mappers.getMapper(OrganizationWiseCertInfoMapper.class);

    OrganizationEntity toEntity(OrganizationWiseCertInfoDto organizationWiseCertInfoDto);

    OrganizationWiseCertInfoDto toDto(OrganizationEntity organizationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrganizationEntity partialUpdate(OrganizationWiseCertInfoDto organizationWiseCertInfoDto, @MappingTarget OrganizationEntity organizationEntity);
}
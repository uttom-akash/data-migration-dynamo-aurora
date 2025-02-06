package com.bkash.savings.models.postgres.nominee.mapper;

import com.bkash.savings.models.postgres.nominee.NomineeEntity;
import com.bkash.savings.models.postgres.nominee.dto.NomineeDto;
import com.github.f4b6a3.uuid.UuidCreator;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NomineeMapper {
    @Mapping(target = "lastUsedTime", ignore = true)
    NomineeEntity toEntity(NomineeDto nomineeDto);

    @AfterMapping
    default void setFields(NomineeDto nomineeDto, @MappingTarget NomineeEntity nomineeEntity) {
        if (StringUtils.isNoneBlank(nomineeDto.getId())) {
            nomineeEntity.setId(UuidCreator.fromString(nomineeDto.getId()));
        }
    }

    @Mapping(target = "dob", source = "dob")
    NomineeDto toDto(NomineeEntity nomineeEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NomineeEntity partialUpdate(NomineeDto nomineeDto, @MappingTarget NomineeEntity nomineeEntity);
}
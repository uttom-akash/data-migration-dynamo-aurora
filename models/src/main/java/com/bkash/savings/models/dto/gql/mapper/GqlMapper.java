package com.bkash.savings.models.dto.gql.mapper;

import com.bkash.savings.models.dto.gql.Customer;
import com.bkash.savings.models.dto.gql.CustomerKyc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface GqlMapper {
    @Mapping(target = "walletNo", source = "data.getKycInfo.msisdn")
    @Mapping(target = "profession", source = "data.getKycInfo.profession")
    @Mapping(target = "nidNumber", source = "data.getKycInfo.nid")
    @Mapping(target = "motherName", source = "data.getKycInfo.mothersName")
    @Mapping(target = "gender", source = "data.getKycInfo.gender")
    @Mapping(target = "fatherName", source = "data.getKycInfo.fathersName")
    @Mapping(target = "dob", source = "data.getKycInfo.dateOfBirth")
    @Mapping(target = "applicantName", source = "data.getKycInfo.fullName")
    @Mapping(target = "address", source = "data.getKycInfo.address")
    Customer fromKYC(CustomerKyc customerKyc);
}

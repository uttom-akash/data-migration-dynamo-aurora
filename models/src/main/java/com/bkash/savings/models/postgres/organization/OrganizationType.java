package com.bkash.savings.models.postgres.organization;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;

public enum OrganizationType {
    REGULAR("REGULAR"),
    ISLAMIC("ISLAMIC");
	
	final String orgType;
	
	OrganizationType(String orgType) {
		this.orgType = orgType;
	}
	
	public static OrganizationType fromOrgType(String orgType) {
		for (var type :OrganizationType.values()) {
			if (orgType.equals(type.orgType)) return type;
		}
		
		throw new SavingsException(ApiResponseStatus.NOT_FOUND.code(), "No OrganizationType found using "+orgType);
	}
}

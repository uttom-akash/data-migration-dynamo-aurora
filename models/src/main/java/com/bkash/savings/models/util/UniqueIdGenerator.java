package com.bkash.savings.models.util;

import com.bkash.savings.common.utils.DateTimeUtils;
import com.github.f4b6a3.uuid.UuidCreator;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.time.ZonedDateTime;

public class UniqueIdGenerator {

	public static String getRequestId() {
        return UuidCreator.getTimeOrderedWithRandom().toString().replace("-", "");
    }
	
	public static String getCorrelationId(String prefix, String suffix) {
		return new StringBuilder().append(prefix)
				   .append("_")
				   .append(getRequestId())
				   .append("_")
				   .append(suffix)
				   .toString();
	}

	public static String uniqueResponseIdForCIMT() {
		return Constants.CIMT +
			   DateTimeUtils.toFiOrCimtDateFormat(ZonedDateTime.now()) +
			   RandomStringUtils.random(5, 0, 0, true, false, null, new SecureRandom());
	}
}

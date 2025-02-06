package com.bkash.savings.models.util;

import com.bkash.savings.models.dto.subscription.SubscriptionFrequency;
import com.bkash.savings.models.postgres.product.Term;

import java.util.Map;

public class TermAndFrequencyUtils {

    private TermAndFrequencyUtils() {}

    private static final Map<String, SubscriptionFrequency> map = Map.of(
            Term.WEEKLY.name(), SubscriptionFrequency.WEEKLY,
            Term.BIWEEKLY.name(), SubscriptionFrequency.FIFTEEN_DAYS,
            Term.MONTHLY.name(), SubscriptionFrequency.CALENDAR_MONTH
    );

    public static SubscriptionFrequency getSubscriptionFrequency(String term) {
        return map.get(term);
    }
}

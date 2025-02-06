package com.bkash.savings.models.dto.dps;


import lombok.Getter;

@Getter
public enum InstalmentStatus {
    IRREGULAR_PAID("IP", "Irregular Paid"),
    IN_PROGRESS("I", "In Progress"),
    PAID("P", "Instalment Completed"),
    MISSED("M", "Missed Instalment"),
    NEXT("N", "Next Instalment"),
    UNPAID("U", "Upcoming");


    private final String key;
    private final String description;

    InstalmentStatus(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public static String mapBankGivenStatusToOur(String installmentStatus) {
        return switch (installmentStatus) {
            case "U" -> InstalmentStatus.UNPAID.name();
            case "P" -> InstalmentStatus.PAID.name();
            case "M" -> InstalmentStatus.MISSED.name();
            case "N" -> InstalmentStatus.NEXT.name();
            case "IP" -> InstalmentStatus.IRREGULAR_PAID.name();
            case "I" -> InstalmentStatus.IN_PROGRESS.name();
            default -> "";
        };
    }

    public static String mapOurStatusToBankGiven(String installmentStatus) {
        return switch (installmentStatus) {
            case "UNPAID" -> InstalmentStatus.UNPAID.key;
            case "PAID" -> InstalmentStatus.PAID.key;
            case "MISSED" -> InstalmentStatus.MISSED.key;
            case "NEXT" -> InstalmentStatus.NEXT.key;
            case "IN_PROGRESS" -> InstalmentStatus.IN_PROGRESS.key;
            case "IRREGULAR_PAID" -> InstalmentStatus.IRREGULAR_PAID.key;
            default -> "";
        };
    }
}

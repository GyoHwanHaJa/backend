package com.exchangeBE.exchange.entity.Report;

public enum ReportType {
    DAILY("생활보고서"),
    INTERIM("중간보고서"),
    FINAL("마감보고서");

    private final String displayName;

    ReportType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

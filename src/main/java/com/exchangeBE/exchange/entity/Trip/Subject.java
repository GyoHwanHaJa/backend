package com.exchangeBE.exchange.entity.Trip;

public enum Subject {
    COST_EFFECTIVENESS("cost-effectiveness"),
    LOCAL("local"),
    MUST_EAT_RESTAURANT("must-eat restaurant"),
    HISTORY("history"),
    LONG_TERM_TRIP("long-term-trip");

    private final String displayName;

    Subject(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

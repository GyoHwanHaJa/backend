package com.exchangeBE.exchange.entity.trip;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }


    @JsonCreator
    public static Subject fromDisplayName(String displayName) {
        for (Subject subject : values()) {
            if (subject.getDisplayName().equals(displayName)) {
                return subject;
            }
        }
        throw new IllegalArgumentException("Unknown display name: " + displayName);
    }
}

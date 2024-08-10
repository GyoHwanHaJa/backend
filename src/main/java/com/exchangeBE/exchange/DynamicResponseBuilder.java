package com.exchangeBE.exchange;

import java.util.HashMap;
import java.util.Map;

public class DynamicResponseBuilder {
    public static Map<String, Object> buildResponse(Object... keyValuePairs) {
        Map<String, Object> response = new HashMap<String, Object>();

        for (int i = 0; i < keyValuePairs.length; i += 2) {
            String key = (String) keyValuePairs[i];
            Object value = keyValuePairs[i + 1];
            response.put(key, value);
        }

        return response;
    }
}

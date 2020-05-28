package net.jonathangiles.tools.teenyhttpd.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class QueryParams {
    public static final QueryParams EMPTY = new QueryParams("");

    private final String allParams;
    private Map<String, String> map;

    public QueryParams(final String allParams) {
        this.allParams = allParams;
    }

    public Map<String, String> getQueryParams() {
        if (map == null) {
            if (allParams == null || allParams.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                final Map<String, String> tempMap = new HashMap<>();

                for (final String param : allParams.split("&")) {
                    final String[] keyValue = param.split("=", 2);

                    try {
                        final String key = URLDecoder.decode(keyValue[0], "UTF-8");
                        final String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8") : "";
                        if (!key.isEmpty()) {
                            tempMap.put(key, value);
                        }
                    } catch (UnsupportedEncodingException e) {
                        // ignore
                    }
                }

                this.map = Collections.unmodifiableMap(tempMap);
            }
        }
        return map;
    }

    public String toString() {
        return getQueryParams().toString();
    }
}
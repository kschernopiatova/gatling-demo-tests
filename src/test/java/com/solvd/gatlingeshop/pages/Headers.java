package com.solvd.gatlingeshop.pages;

import io.gatling.http.HeaderNames;
import io.gatling.http.HeaderValues;

import java.util.Map;

public class Headers {

    public static Map<CharSequence, String> headersGeneral = Map.ofEntries(
            Map.entry(HeaderNames.Accept(), HeaderValues.ApplicationJson()),
            Map.entry(HeaderNames.ContentType(), HeaderValues.ApplicationJson())
    );
}

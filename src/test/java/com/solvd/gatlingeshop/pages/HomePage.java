package com.solvd.gatlingeshop.pages;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class HomePage {

    public static ChainBuilder open = exec(
        http("Get home Page")
                .get("/eshop")
                .headers(Headers.headersGeneral)
                .check(status().is(200)));
}

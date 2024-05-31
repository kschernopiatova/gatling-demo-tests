package com.solvd.gatlingeshop.simulations;

import io.gatling.javaapi.core.Assertion;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.util.List;

import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.http.HttpDsl.http;

public abstract class BasicSimulation extends Simulation {

    protected static final HttpProtocolBuilder HTTP_PROTOCOL = http
            .baseUrl("http://eshop:8888")
            .contentTypeHeader("application/json")
            .acceptHeader("application/json");

    protected static final List<Assertion> assertions = List.of(
            global().successfulRequests().percent().gt(95.0),
            global().responseTime().percentile2().lt(100));
}

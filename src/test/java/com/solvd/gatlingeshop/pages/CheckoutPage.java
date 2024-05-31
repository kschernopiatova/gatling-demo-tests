package com.solvd.gatlingeshop.pages;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.ElFileBody;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class CheckoutPage extends AbstractPage{

    public static ChainBuilder submitPurchase = exec(
            setSessionVariablesForCheckout,
            http("Submit purchase")
                    .post("/eshop/control/checkout/purchase/submit")
                    .body(ElFileBody("json/submitPurchase.json")).asJson()
                    .headers(Headers.headersGeneral)
                    .check(status().is(200)));
}

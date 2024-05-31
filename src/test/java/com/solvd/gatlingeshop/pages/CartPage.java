package com.solvd.gatlingeshop.pages;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.List;
import java.util.Random;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class CartPage extends AbstractPage {

    public static ChainBuilder addItemToCart =
            exec(session -> {
                List<String> products = session.getList("products");
                String randomProduct = products.get(new Random().nextInt(products.size()));
                return session.set("randomProduct", randomProduct);
            })
                    .exec(http("Add to cart")
                            .post("/eshop/control/cart/additem")
                            .body(ElFileBody("json/addItemToCart.json")).asJson()
                            .headers(Headers.headersGeneral)
                            .check(status().is(200)));

    public static ChainBuilder getCartInfo = exec(
            http("Get cart info")
                    .get("/eshop/control/cart/getInfo")
                    .headers(Headers.headersGeneral)
                    .check(status().is(200))
                    .check(jsonPath("$.cartInfo.cartItems..id").ofInt().findAll().saveAs("productsInCart"))
                    .check(jmesPath("cartInfo.cartItems[*].[product.id,quantity]").saveAs("IdQuantity")));

    public static ChainBuilder removeItemFromCart = exec(
            setSessionVariablesForRemovingItem,
            http("Remove from cart")
                    .delete("/eshop/control/cart/removeitem")
                    .check(status().saveAs("statusCode"))
                    .body(ElFileBody("json/removeItemFromCart.json")).asJson()
                    .headers(Headers.headersGeneral)
                    .check(status().is(200))
                    .check(jsonPath("$.removedProductId").is(session -> session.getString("selectedProductId")))
                    .check(bodyString().saveAs("responseBody")))
            .exec(session -> {
                if (session.getInt("statusCode") != 200) {
                    String responseBody = session.get("responseBody");
                    System.out.println(responseBody);
                }
                return session;
            });

    public static ChainBuilder updateQuantity = exec(
            setSessionVariablesForUpdatingItem,
            http("Update quantity")
                    .post("/eshop/control/cart/updatequantity")
                    .body(ElFileBody("json/updateQuantity.json")).asJson()
                    .headers(Headers.headersGeneral)
                    .check(
                            status().is(200),
                            jsonPath(session -> "$.cartInfo.cartItems[?(@.product.id=="
                                    + session.get("selectedProductId") + ")].quantity")
                                    .is(session -> session.getString("quantity"))
                    ));
}

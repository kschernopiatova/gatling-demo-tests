package com.solvd.gatlingeshop.pages;

import com.solvd.gatlingeshop.util.Randomizer;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.Session;

import static io.gatling.javaapi.core.CoreDsl.exec;

public class AbstractPage {

    protected static ChainBuilder setSessionVariablesForRemovingItem = exec(
            session -> session.set("selectedProductId",
                    Randomizer.getIntFromSeq(session.getList("productsInCart")))
    );

    protected static ChainBuilder setSessionVariablesForUpdatingItem = exec(
            session -> {
                Session newSession = session.set("quantity", Randomizer.getInt(2, 10));
                return newSession.set("selectedProductId",
                        Randomizer.getIntFromSeq(session.getList("productsInCart")));
            });

    protected static ChainBuilder setSessionVariablesForCheckout = exec(
            session -> session.set("name", Randomizer.getName())
            .set("email", Randomizer.getEmail())
            .set("phone", Randomizer.getInt(9000000, 9999999))
            .set("address", Randomizer.getAddress())
            .set("ccNumber", Randomizer.getInt(1000000, 9999999)));
}

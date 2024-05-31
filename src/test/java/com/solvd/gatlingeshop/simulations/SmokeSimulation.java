package com.solvd.gatlingeshop.simulations;

import com.solvd.gatlingeshop.FullPathBuyScenario;

import static io.gatling.javaapi.core.CoreDsl.*;

public class SmokeSimulation extends BasicSimulation {

    {
        setUp(FullPathBuyScenario.scn.injectOpen(
                        constantUsersPerSec(1).during(30))
                .protocols(HTTP_PROTOCOL))
                .assertions(assertions);
    }
}

package com.solvd.gatlingeshop.simulations;

import com.solvd.gatlingeshop.FullPathBuyScenario;

import static io.gatling.javaapi.core.CoreDsl.*;

public class SoakSimulation extends BasicSimulation {

    {
        setUp(FullPathBuyScenario.scn.injectClosed(
                    rampConcurrentUsers(0).to(150).during(20),
                    constantConcurrentUsers(150).during(300))
                .protocols(HTTP_PROTOCOL))
                .assertions(assertions);
    }
}

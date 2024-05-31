package com.solvd.gatlingeshop.simulations;

import com.solvd.gatlingeshop.FullPathBuyScenario;

import static io.gatling.javaapi.core.CoreDsl.*;

public class StressSimulation extends BasicSimulation {

    {
        setUp(FullPathBuyScenario.scn.injectClosed(
                        rampConcurrentUsers(0).to(300).during(10),
                        constantConcurrentUsers(300).during(30)
                )
                .protocols(HTTP_PROTOCOL))
                .assertions(assertions);
    }
}

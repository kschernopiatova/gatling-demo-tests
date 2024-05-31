package com.solvd.gatlingeshop.simulations;

import com.solvd.gatlingeshop.FullPathBuyScenario;

import static io.gatling.javaapi.core.CoreDsl.rampConcurrentUsers;

public class BreakpointSimulation extends BasicSimulation {

    {
        setUp(FullPathBuyScenario.scn.injectClosed(
                        rampConcurrentUsers(0).to(1500).during(60))
                .protocols(HTTP_PROTOCOL))
                .assertions(assertions);
    }
}

package com.solvd.gatlingeshop.simulations;

import com.solvd.gatlingeshop.FullPathBuyScenario;

import static io.gatling.javaapi.core.CoreDsl.*;

public class SpikeSimulation extends BasicSimulation {

    {
        setUp(FullPathBuyScenario.scn.injectClosed(
                        rampConcurrentUsers(0).to(400).during(20))
                .protocols(HTTP_PROTOCOL))
                .assertions(assertions);
    }
}

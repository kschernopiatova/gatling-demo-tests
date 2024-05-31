package com.solvd.gatlingeshop.simulations;

import com.solvd.gatlingeshop.FullPathBuyScenario;

import static io.gatling.javaapi.core.CoreDsl.*;

public class LoadSimulation extends BasicSimulation {

    {
        setUp(FullPathBuyScenario.scn.injectOpen(
                rampUsers(10).during(20),
                nothingFor(5),
                constantUsersPerSec(10).during(5),
                rampUsersPerSec(10).to(20).during(10))
                .protocols(HTTP_PROTOCOL))
                .assertions(assertions);
    }
}

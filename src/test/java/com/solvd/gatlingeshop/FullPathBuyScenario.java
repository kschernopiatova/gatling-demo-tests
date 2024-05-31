package com.solvd.gatlingeshop;

import com.solvd.gatlingeshop.pages.CartPage;
import com.solvd.gatlingeshop.pages.CategoriesPage;
import com.solvd.gatlingeshop.pages.CheckoutPage;
import com.solvd.gatlingeshop.pages.HomePage;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.scenario;

public class FullPathBuyScenario {

    public static final ScenarioBuilder scn = scenario("FullPathBuyScenario")
            .exec(HomePage.open)
            .exec(CategoriesPage.getCategories)
            .exec(CategoriesPage.getCategoryProducts)
            .exec(CartPage.addItemToCart)
            .exec(CategoriesPage.getCategoryProducts)
            .exec(CartPage.addItemToCart)
            .exec(CartPage.getCartInfo)
            .exec(CartPage.updateQuantity)
            .exec(CartPage.getCartInfo)
            .exec(CartPage.removeItemFromCart)
            .exec(CheckoutPage.submitPurchase);

}

package com.solvd.gatlingeshop.pages;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.List;
import java.util.Random;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class CategoriesPage {

    public static ChainBuilder getCategories = exec(
            http("Get categories")
                    .get("/eshop/control/category/all")
                    .headers(Headers.headersGeneral)
                    .check(status().is(200))
                    .check(jsonPath("$.categories").exists())
                    .check(jsonPath("$.categories").notNull())
                    .check(jsonPath("$.categories[*]").count().is(6))
                    .check(jsonPath("$..id").findAll().saveAs("categories")));

    public static ChainBuilder getCategoryProducts =
            exec(session -> {
                List<String> categories = session.getList("categories");
                String category = categories.get(new Random().nextInt(categories.size()));
                return session.set("category", category);
            })
                    .exec(
                            http("Get category products")
                                    .get("/eshop/control/category/#{category}/products")
                                    .headers(Headers.headersGeneral)
                                    .check(status().is(200))
                                    .check(jsonPath("$..id").findAll().saveAs("products")));
}

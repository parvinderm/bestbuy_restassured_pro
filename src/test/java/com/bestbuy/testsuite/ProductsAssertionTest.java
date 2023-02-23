package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasKey;

public class ProductsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }
    //1)Verify the if the total is equal to 51957
    @Test
    public void test001(){
        response.body("total",equalTo(51957));

    }
    //2)Verify the if the stores of limit is equal to 10
    @Test
    public void test002(){
        response.body("limit",equalTo(10));
    }
    //3)Check the single'Name'in the Array list(Duracell-AAA Batteries(4-Pack))
    @Test
    public void test003(){
        response.body("data.name",hasItem("Duracell - AAA Batteries (4-Pack)"));

    }
    //4)c
    @Test
    public void test004(){
        response.body("data.name",hasItem("Duracell - AA 1.5V CopperTop Batteries (4-Pack)"));
        response.body("data.name",hasItem("Duracell - AA Batteries (8-Pack)"));
        response.body("data.name",hasItem("Energizer - MAX Batteries AA (4-Pack)"));

    }
    //5)Verify the productID = 150115 inside categories of the third name is "Household Batteries
    @Test
    public void test005(){
     response.body("data[4].categories[3]",hasKey("id"));
    }
    //6
    @Test
    public void test006(){
        response.body("data[9].categories[2]",hasKey("id"));
    }
    //7
    @Test
    public void test007(){
        Arrays.asList("data[3].price", equalTo(4.99));
    }
    //8
    @Test
    public void test008(){
        response.body("data[5].name",equalTo("Duracell - D Batteries (4-Pack)"));
    }
    //9 Verify the ProductId =333719 for the 9th product
    @Test
    public void test009(){
        Arrays.asList("data[8].id", equalTo(333719));

    }
    //10 Verify the productId = 346575 have 5 categories
    @Test
    public void test010(){
        Arrays.asList("data[9].categories", equalTo(5));
    }



}

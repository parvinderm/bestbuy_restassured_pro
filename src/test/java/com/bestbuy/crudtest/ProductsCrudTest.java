package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductsCrudTest extends TestBase {
    int idNumber;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }

    @Test // get all list
    public void test001() {

        given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);
    }

    @Test // post new and retrive id
    public void test002() {

        ProductPojo pojo = new ProductPojo();
        pojo.setName("krishe");
        pojo.setType("Softgood");
        pojo.setPrice(1677);
        pojo.setShipping(34);
        pojo.setUpc("435677");
        pojo.setDescription("tesp");
        pojo.setManufacturer("fordyt");
        pojo.setModel("thyug");
        pojo.setUrl("cdc");
        pojo.setImage("sdfv");


        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(pojo)
                .post();
        response.then().statusCode(201);
        int idNumber = response.then().extract().path("id");

        System.out.println(idNumber);

    }
     @Test//update id
      public void test003(){
        ProductPojo pojo=new ProductPojo();
        pojo.setPrice(1600);
        pojo.setShipping(245);
        pojo.setManufacturer("ford");
        pojo.setModel("audis");

        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","9999690")
                .when()
                .body(pojo)
                .patch("/{id}");
        response.then().statusCode(200);

    }
    @Test//delete it
    public void test004(){
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","9999690")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }
    @Test//retrive id and validate
    public void test005(){
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","9999690")
                .when()
                .get("/{id}");
         response.then().statusCode(404);
    }



}

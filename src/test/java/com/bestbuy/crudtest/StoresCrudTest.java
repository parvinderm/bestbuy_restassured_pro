package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCrudTest extends TestBase {
    int idNumber;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
    }

    @Test  //get all list
    public void test001() {

        given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);
    }

    @Test //post new and retrive id
    public void test002() {
        StorePojo pojo = new StorePojo();
        pojo.setName("Tanu");
        pojo.setType("SmallBox");
        pojo.setAddress("1245 Dale Avenue");
        pojo.setAddress2("567 Hash ");
        pojo.setCity("Harrow");
        pojo.setState("RS");
        pojo.setZip("gtjui");
        pojo.setLat(87656);
        pojo.setLng(65434);
        pojo.setHours("Tue:9-5; Wed: 10-6; Sat:10:4;");
//        pojo.setServices("rt");

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
    @Test //update id
    public void test003(){
        StorePojo pojo = new StorePojo();
        pojo.setName("Radhu");
        pojo.setType("GreenBox");
        pojo.setAddress("52 shar");
        pojo.setAddress2("345 Carme Road");

        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","8921")
                .when()
                .body(pojo)
                .patch("/{id}");
        response.then().statusCode(200);


    }
    @Test //delete it
    public void test004(){
        Response response = given()
                .log().all()
                .header("Content -Type","application/json")
                .pathParam("id","8921")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }

    @Test //retrieve id and validate
    public void test005(){
        Response response=given()
                .log().all()
                .header("Content Type","application/json")
                .pathParam("id","8921")
                .when()
                .get("/{id}");
        response.then().statusCode(404);

    }


}

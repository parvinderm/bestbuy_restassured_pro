package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ProductsExtractionTest {
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
    //Extract the limit
    @Test
    public void test01(){
        int limit = response.extract().path("limit");
        System.out.println("value of limit is : "+limit);
        Assert.assertEquals(10,limit);
        response.body("limit",equalTo(10));
    }
    //Extract the total
    @Test
    public void test02(){
        int total=response.extract().path("total");
        System.out.println("value of total is :" +total);
        Assert.assertEquals(51957,total);
        response.body("total",equalTo(51957));
    }
    //3 Extract the name of 5th product
    @Test
    public void test03(){
        String nameOfStore=response.extract().path("data[4].name");
        System.out.println(nameOfStore);
    }
    //4Extract the name of all products
    public void test04(){
        List<String> namesOfAllStore=response.extract().path("data.name");
        System.out.println("list of all stores name  :"+namesOfAllStore);
        for(String a:namesOfAllStore)
            if(a.equals(51957)){
                Assert.assertTrue(true);
            }
    }
    //5
    @Test
    public void test05(){
        List<Integer>idOfAllStore=response.extract().path("data.id");
        System.out.println("List of Id of all the stores :"+idOfAllStore);
        for(Integer b:idOfAllStore)
            if (b.equals(51957)){
                Assert.assertTrue(true);
            }
    }
    //6Print the size of all the data list
    @Test
    public void test06(){
        List<Integer>dataSize=response.extract().path("data");
        int size=dataSize.size();
        System.out.println(size);

    }
    //7
    @Test
    public void test07(){
        List<HashMap<String,?>>values=response.extract().path("data.findAll{it.name =='Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println(values);
    }
    //8
    @Test
    public void test08(){
        List<String>modelOfTheProduct=response.extract().path("data.findAll{it.name =='Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println(modelOfTheProduct);
    }
    //9 Get all the categories of 8th products
    @Test
    public void test09(){
        List<HashMap<String,?>>categories=response.extract().path("data[7].categories");
        System.out.println(categories);
    }
    //10)Get categories of the store where product id = 150115
    @Test
    public void test10(){
        List<HashMap<?, ?>> categories = response.extract().path("data.findAll{it.id ==150115}.categories");
        System.out.println(categories);
    }
    //11 Get all the descriptions of all the products
    @Test
    public void test11(){
        List<Integer>descriptionOfAllTheProducts=response.extract().path("data");
        System.out.println(" description of all the products:"+descriptionOfAllTheProducts);

    }
    //12 Get id of all categories of all the products
    @Test
    public void test12(){
        List<Integer>idOfAllCategories=response.extract().path("data.categories.id");
        System.out.println(" id of all the products :"+idOfAllCategories);

    }
    //13)
    @Test
    public void test13(){
        List<String>productNames=response.extract().path("data.findAll{it.type=='HardGood'}.name");
        System.out.println(productNames);
    }
    //14
    @Test
    public void test14(){
        List<Integer> totalNum=response.extract().path("data.findAll{it.name='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println(totalNum);
    }
    //15
    @Test
    public void test15(){
        List<Integer>createdAt=response.extract().path("data.findAll{it.price<5.49}.createdAt");
        System.out.println(createdAt);
    }
    //16
    @Test
    public void test16(){
        List<String>name=response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println(name);

    }
    //17)
    @Test
    public void test17(){
        List<Integer>manufacturerOfProducts=response.extract().path("data.manufacturer");
        System.out.println(manufacturerOfProducts);
    }
    //18)
    @Test
    public void test18(){
        List<Integer>imageOfProducts=response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");
        System.out.println(imageOfProducts);

    }
    //19
    @Test
    public void test19(){
        List<Integer>createdAt=response.extract().path("data.findAll{it.price>5.99}.createdAt");
        System.out.println(createdAt);

    }
    //20)
    @Test
    public void test20(){
        List<?>uriOfAllTheProducts=response.extract().path("data.url");
        System.out.println(uriOfAllTheProducts);
    }
}

package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI="http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
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
        Assert.assertEquals(1561,total);
        response.body("total",equalTo(1561));
    }
    //3)Extract The name of 5th store
    @Test
    public void test03(){
        String nameOfStore=response.extract().path("data[4].name");
        System.out.println(nameOfStore);
    }
    //4)Extracts the names of all the store
    @Test
    public void test04(){
        List<String>namesOfAllStore=response.extract().path("data.name");
        System.out.println("list of all stores name  :"+namesOfAllStore);
        for(String a:namesOfAllStore)
            if(a.equals(1561)){
                Assert.assertTrue(true);
            }
    }

    //5)Extract the storeId of all the store
    @Test
    public void test05(){
        List<Integer>idOfAllStore=response.extract().path("data.id");
        System.out.println("List of Id of all the stores :"+idOfAllStore);
        for(Integer b:idOfAllStore)
            if (b.equals(1561)){
                Assert.assertTrue(true);
            }
    }
    //6)Print the size of the data list
    @Test
    public void test06(){
        List<Integer>dataSize=response.extract().path("data");
        int size=dataSize.size();
        System.out.println(size);
    }
    //7)Get all the value of the store where store name = St Cloud
    @Test
    public void test07(){
        List<HashMap<String,?>>values=response.extract().path("data.findAll{it.name =='St Cloud'}");
        System.out.println(values);
    }
    //8)Get the address of the store where store name = Rochhester
    @Test
    public void test08(){
        List<String>address=response.extract().path("data.findAll{it.name =='Rochester'}.address");
        System.out.println(address);
    }
    //9)Get all the services of 8th store
    @Test
    public void test09(){
       List<HashMap<String,?>>services=response.extract().path("data[7].services");
        System.out.println(services);
    }
    //10)Get storeservices of the store where service name=Windows Store
    @Test
    public void test010(){
        List<HashMap<?, ?>> storeservice = response.extract().path("data.findAll{it.storeservices ='Windows Store'}.services");
        System.out.println(storeservice);

    }
    //11)Get all the storeId of all the store
    @Test
    public void test011(){
        List<Integer>storeIdOfAllStore=response.extract().path("data.services.storeservices.storeId");
        System.out.println(" storeId of all the stores :"+storeIdOfAllStore);
    }
    //12)get id of all the store
    @Test
    public void test012(){
        List<Integer>idOfAllStore=response.extract().path("data.id");
            System.out.println(idOfAllStore);

    }
    //13)Find the store names Where state=ND
    @Test
    public void test013(){
        List<String>storeName=response.extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println(storeName);
    }
    //14)Find the total number of services for the store where store name = Rochester
    @Test
    public void test014(){
        List<Integer> totalNum=response.extract().path("data.findAll{it.name='Rochester'}.services");
        System.out.println(totalNum);
    }
    //15)Find the createdAt for all services whose name="Windows Store"
    @Test
    public void test015(){
        List<Integer>createdAt=response.extract().path("data.findAll{it.name='Windows Store'}.services");
        System.out.println(createdAt);
    }
    //16)Find the name of all services Where store name ="Fargo"
    @Test
    public void test016(){
        List<String>name=response.extract().path("data.findAll{it.name=='Fargo'}.services");
        System.out.println(name);
    }
    //17)Find the zip of all the store
    @Test
    public void test017(){
        List<Integer>zipOfAllTheStore=response.extract().path("data.zip");
        System.out.println(zipOfAllTheStore);

    }
    //18)Find the zip of store name = Roseville
    @Test
    public void test018(){
        List<Integer>zipOfStore=response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println(zipOfStore);
    }
    //19)Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019(){
        List<?>storeservicesdetails=response.extract().path("data.findAll{it.name='Magnolia Home Theater'}.services");
        System.out.println(storeservicesdetails);
    }
    //20 Find the lat of all the stores
    @Test
    public void test020(){
        List<?>latOfAllTheStores=response.extract().path("data.lat");
        System.out.println(latOfAllTheStores);
    }









}

package com.test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DefaultRequestAndResponseSpecification {
    // we use RestAssured.request as a static method to define the default values
    // similary we can use response specification but respoinse specification requires expect() otherwise it will not work
    // Request and response builder is the best solution of all

    @BeforeClass
    public void setup()
    {
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        RestAssured.requestSpecification = requestSpecBuilder.setBaseUri("https://api.postman.com").addHeader("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f").build();
      //  RestAssured.requestSpecification = given().baseUri("https://api.postman.com").header("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f").log().all();
    // RestAssured.responseSpecification =expect().log().all().statusCode(200).contentType(ContentType.JSON);
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        RestAssured.responseSpecification = responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @Test
    public void defaultClass()
    {
        given().when().get("/workspaces").then();
    }

    @Test
    public void ResponseSpecification()
    {
        given().when().get("/workspaces").then().log().all();
    }
}

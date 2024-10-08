package com.test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class RequestSpecificationExample {
    RequestSpecification requestSpecification2;
    @BeforeClass
    public void setup()
    {
        requestSpecification2=given().header("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f").baseUri("https://api.postman.com").when();
        //we can use with() too but this is just for conveninec both of them work same
    }
    @Test
    public void requestSpecificationExample1()
    {
        RequestSpecification requestSpecification =given().header("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f").baseUri("https://api.postman.com").when();

        requestSpecification.get("/workspaces").then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void testRequsability()
    {
        given(requestSpecification2).get("/workspaces").then().log().all().assertThat().statusCode(200);
    }
    @Test
    public void testNonBDD()
    {
        Response response = requestSpecification2.get("/workspaces").then().log().all().extract().response();
        assertThat(response.path("workspaces[0].id"), equalTo("0a6deece-ccec-4b81-888c-ecdc36cf7825"));
    }
    // we need given().spec() to properly use any template
    @Test
    public void requestSpecifictionBuilder()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
       RequestSpecification requestSpecification1 = requestSpecBuilder.addHeader("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f").setBaseUri("https://api.postman.com").build();
        given().spec(requestSpecification1).get("/workspaces").then().log().all().assertThat().statusCode(200);

    }
    @Test
    public void testReuablity2()
    {

            given().spec(requestSpecification2).get("/workspaces").then().log().all().assertThat().statusCode(200);

    }
}

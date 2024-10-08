package com.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
// we are using before class for global definition and use beforeMethod for setting up the chaining

public class PostAndDeleteRequestTest {
   static String id;
    @BeforeClass

    public void setUp()
    {
        RequestSpecBuilder requestSpecBuilder =new RequestSpecBuilder();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        requestSpecification = requestSpecBuilder.setBaseUri("https://api.postman.com").addHeader("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f").build();
       // responseSpecification= responseSpecBuilder.expectContentType(ContentType.JSON).expectStatusCode(200).build();
    }
    @BeforeMethod
    public void testPostBDD()
    {
        // Here intellij has intelligently formatted the string
        String bodypost = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"TestWorkspace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"This is test workspace\"\n" +
                "    }\n" +
                "}";
       Response response= given().when().body(bodypost).post("/workspaces").then().assertThat().log().all().body("workspace.id", matchesRegex("[0-9A-Za-z-]{36}")).statusCode(200).extract().response();

        id= response.path("workspace.id");
        System.out.println(id);
        //if we want to define not then we can define it by [^0-9A-Za-z]
    }
    @Test
    public void deleteRequest()
    {
        given().when().delete("/workspaces/" + id).then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void testPOSTNonBDD()
    {
        // Here intellij has intelligently formatted the string
        String bodypost = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"TestWorkspace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"This is test workspace\"\n" +
                "    }\n" +
                "}";
        // matchers expression has variable name followed by type of comparison in Matchers assertions
        Response response = requestSpecification.body(bodypost).post("/workspaces");
        assertThat(response.path("workspace.id"), matchesRegex("[0-9A-Za-z-]{36}"));
        assertThat(response.statusCode(), equalTo(200));
    }

}

package com.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class PUTRequest {
    @BeforeClass
    public void Setup()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        requestSpecification = requestSpecBuilder.setBaseUri("https://api.postman.com").addHeader("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f").build();
        responseSpecification = responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @Test
    public void PUTtest()
    {
        String pathparm = "f3585367-07a8-46f1-9e32-81e897fadf1b";
        String putbody= "{\n" +
                "    \"workspace\": {\n" +
                "        \"id\": \"05dbcf05-4ef6-4476-b593-a8e4869cf43d\",\n" +
                "        \"name\": \"TestWorkspaceModified\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"This is test workspace\",\n" +
                "        \"visibility\": \"personal12\",\n" +
                "        \"createdBy\": \"20979684\",\n" +
                "        \"updatedBy\": \"20979684\",\n" +
                "        \"createdAt\": \"2024-09-30T13:55:10.000Z\",\n" +
                "        \"updatedAt\": \"2024-09-30T13:55:10.000Z\"\n" +
                "    }\n" +
                "}";
        given().body(putbody).when().put("/workspaces/" + pathparm).then().log().all().assertThat().body("workspace.name", is(equalTo("TestWorkspaceModified")));

    }
}

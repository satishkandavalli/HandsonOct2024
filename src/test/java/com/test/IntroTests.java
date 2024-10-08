package com.test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.testng.annotations.Test;


public class IntroTests {
    //static imports improve readability but it is not recommeneded
    //method chaining works when each method returns same object
    //given() and when() defines request specification
    //request specification is an interface which contains all the methods to build a request
    // Rest assured supports grrovy json path
    //For hasItems order doesnt matter
    //For hasItems it need not have all the contents just one content is fine
    //asString() method is provided by rest assured so it will work
    //equals to method is used to match exact value
    // is infront of equal to is just for readability
    // Hamcrest matchers are for readability
@Test
    public void testRest()
{
    given().baseUri("https://api.postman.com").header("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f")
            .when().get("/workspaces")
            .then().log().all().assertThat().statusCode(200)
            .body("workspaces.name", hasItems("TestWorkspaceModified","APITestingFramework", "TestWorkspace2", "TestWorkspace3"),
                    "workspaces.name", hasItems("APITestingFramework"))
          .body("workspaces.id", hasItem("f3585367-07a8-46f1-9e32-81e897fadf1b"));

}

@Test
public  void ExtractResponse()
{
    Response response = given().baseUri("https://api.postman.com").header("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f")
            .when().get("/workspaces")
            .then().extract().response();
    System.out.println(response.asString());
    System.out.println(response.path("workspaces[3].id").toString());
}
    @Test
    public void HamcrestOutside()
    {
      String id = given().baseUri("https://api.postman.com").header("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f")
              .when().get("/workspaces")
              .then().extract().response().path("workspaces[3].id");
      assertThat(id, is(equalTo("ba0ecacc-7d1a-4025-a94f-a97d49cf0642")));
      assertThat(id, is(not(equalTo("1"))));
    }
}


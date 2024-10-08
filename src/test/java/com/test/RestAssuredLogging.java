package com.test;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RestAssuredLogging {
    @Test
    public void allLogging()
    {
        given().baseUri("https://api.getpostman.com").header("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f").
                when().log().all().get("/workspaces").
                then().log().everything();
    }
    // similarly we have body(), cookies(), headers(), status()
    //log if error logs if there is an error for debugging purpose
    @Test
    public void logifError()
    {
        given().baseUri("https://api.getpostman.com").header("X-API-Key", "1PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f").
                when().log().all().get("/workspaces").
                then().log().ifError();
    }
// only logs if  validation fails
    @Test
    public void logIfValidationFails()
    {
        given().baseUri("https://api.getpostman.com").header("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f").
                when().get("/workspaces").
                then().log().ifValidationFails().assertThat().statusCode(201);
    }
}

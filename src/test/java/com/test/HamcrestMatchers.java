package com.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

//For hasItems order doesnt matter
//For hasItems it need not have all the contents just one content is fine
//asString() method is provided by rest assured so it will work
//equals to method is used to match exact value
// is infront of equal to is just for readability
// Hamcrest matchers are for readability
// contains is used for exact match and order
// contains in any order is to ignore order but not content
// empty() is for collection ()
//emptyArray() is for arrayMap fro JSON
// hasSize() is only for array size, it doesnt work on maps
//hasKey() is used to identify key within the map
//hasvalue() is used to find map has a value
//hasENtry() is used to find if there are matching key value pairs
//equalTo (Collections.EMPTY_MAP)--> MAP{checks if it is empty}
//allof() is used for strings and we can apply string functions here
/*
anyof() --> Matches if any of the matchers matches alone
Numbers:
greaterThanOrEqualTo()
lessThan()
lessThanOrEqualTo()

Strings:
containsString()
emptyString()
 */


public class HamcrestMatchers {
    @Test
    public void testRest()
    {
        given().baseUri("https://api.postman.com").header("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f")
                .when().get("/workspaces")
                .then().log().all().assertThat().statusCode(200)
                .body("workspaces.name", hasItems("TestWorkspaceModified","APITestingFramework", "TestWorkspace2", "TestWorkspace3"),
                        "workspaces.name", hasItems("APITestingFramework"))
                .body("workspaces.id", hasItem("f3585367-07a8-46f1-9e32-81e897fadf1b"),
                "workspaces.id", contains("0a6deece-ccec-4b81-888c-ecdc36cf7825", "f3585367-07a8-46f1-9e32-81e897fadf1b", "016010e9-3aa2-4b09-86ac-0244fec26efd", "ba0ecacc-7d1a-4025-a94f-a97d49cf0642")
                , "workspaces.id", containsInAnyOrder(  "f3585367-07a8-46f1-9e32-81e897fadf1b","0a6deece-ccec-4b81-888c-ecdc36cf7825","016010e9-3aa2-4b09-86ac-0244fec26efd", "ba0ecacc-7d1a-4025-a94f-a97d49cf0642"),
                "workspaces.name" , not(emptyArray()),
                        "workspaces[0]", not(empty()),
                        "workspaces.id", hasSize(4),
                "workspaces.type", everyItem(startsWith("per")));

    }

    @Test
    public void testMAp()
    {
        given().baseUri("https://api.postman.com").header("X-API-Key", "PMAK-66f9d8cc110b6300012c7eea-58b3c5b2ac87181faf854e8defa4f2567f")
                .when().get("/workspaces")
                .then().log().all().assertThat().statusCode(200)
                .body("workspaces[0]", hasKey("name"), "workspaces[0]", hasValue("0a6deece-ccec-4b81-888c-ecdc36cf7825"), "workspaces[0]", hasEntry("name", "APITestingFramework"),
                        "workspaces[0].visibility", allOf(startsWith("per"), containsString("son")));
        ;
    }
}

package com.test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class Headerstest {
    @Test
    public void multiheaderRequest1()
    {
        given().baseUri("https://a66c2fb3-5959-4577-a84c-f9e37f5fdfe7.mock.pstmn.io").header("header", "value2").header("x-mock-match-request-headers", "header").
                when().log().all().get("/get1").
                then().log().all();
    }
    //header class should be from est assured, it has lead to errors
    @Test
    public void multiheaderRequest2()
    {
        Header header1 = new Header("header", "value1");
        Header header2 = new Header("x-mock-match-request-headers", "header");
        given().headers("header1", "header2").baseUri("https://a66c2fb3-5959-4577-a84c-f9e37f5fdfe7.mock.pstmn.io").
                when().get("/get1").
                then().log().all();
    }
    //Map should be passed as map, where header names are passed as strings
    @Test
    public void multiheaderRequest3()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("header", "value2");
        map.put("x-mock-match-request-headers", "header");
        given().headers(map).baseUri("https://a66c2fb3-5959-4577-a84c-f9e37f5fdfe7.mock.pstmn.io").
                when().get("/get1").
                then().log().all();
    }
    @Test
    public void AssertHeader()
    {
        Header header1 = new Header("header", "value2");
        Header header2 = new Header("x-mock-match-request-headers", "header");
        Headers headers = new Headers(header1, header2);
        given().headers(headers).baseUri("https://a66c2fb3-5959-4577-a84c-f9e37f5fdfe7.mock.pstmn.io").
                when().get("/get1").
                then().log().all().assertThat().header("responseheader", "resvalue2");
    }
    @Test
    public void ExtractHeaders()
    {
        Header header1 = new Header("header", "value2");
        Header header2 = new Header("x-mock-match-request-headers", "header");
        Headers headers = new Headers(header1, header2);
        Headers headers1 = given().headers(headers).baseUri("https://a66c2fb3-5959-4577-a84c-f9e37f5fdfe7.mock.pstmn.io").
                when().get("/get1").
                then().log().all().extract().headers();

        Response response =given().headers(headers).baseUri("https://a66c2fb3-5959-4577-a84c-f9e37f5fdfe7.mock.pstmn.io").
                when().get("/get1").
                then().log().all().extract().response();
        String msg = response.path("msg");
        System.out.println(msg);
        for(Header headerN:headers1)
        {
            System.out.println("Header names is " + headerN.getName() + "and  value is " + headerN.getValue());
        }


    }
    @Test
    public void multiValueResponseHeaders()
    {
        Header header1 = new Header("header", "value1");
        Header header2 = new Header("x-mock-match-request-headers", "header");
        Headers headers = new Headers(header1, header2);
      Headers resheaders  =given().headers(headers).baseUri("https://a66c2fb3-5959-4577-a84c-f9e37f5fdfe7.mock.pstmn.io").
                when().get("/getM").
                then().log().all().assertThat().header("responseheader", "resvalue2").extract().headers();
         List<String> samlist= resheaders.getValues("MultiValueHeader");
         for(String str : samlist)
         {
             System.out.println("MultiValueHeader = " + str);
         }

    }

}

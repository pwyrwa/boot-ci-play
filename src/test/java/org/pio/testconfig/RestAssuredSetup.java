package org.pio.testconfig;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

/**
 * <Add description here>
 * <p/>
 * Created 2/15/16.
 */
public class RestAssuredSetup {

    private static int port;

    public RestAssuredSetup() {

        RestAssured.baseURI = "http://localhost";
//        RestAssured.requestSpecification = defaultRequestSpec();
//        RestAssured.responseSpecification = defaultResponseSpecification();
    }


    public static void withPort(int setPort) {
        port = setPort;
    }



    public static RequestSpecification defaultRequestSpec() {
        return  baseRequestSpecBuilder()
                .log(LogDetail.ALL)
                .setContentType(ContentType.JSON.withCharset("UTF-8"))
                .addHeader(HttpHeaders.ACCEPT, ContentType.JSON.toString())
                .build();
    }

    public static ResponseSpecification defaultResponseSpecification() {
        return new ResponseSpecBuilder()
            .expectHeader("Content-Type", "application/json;charset=UTF-8")
            .build();
    }

    public static ResponseSpecification notFoundResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.NOT_FOUND.value())
                .build();
    }

    private static RequestSpecBuilder baseRequestSpecBuilder() {
        return new RequestSpecBuilder().setPort(port);
    }

}

package org.pio.testconfig;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

/**
 * <Add description here>
 * <p/>
 * Created 2/15/16.
 */
public class RestAssuredSetup {


    private RequestSpecification requestSpecification;

    private ResponseSpecification responseSpecification;

    public RestAssuredSetup(RequestSpecification requestSpecification,
            ResponseSpecification responseSpecification) {
        this.requestSpecification = requestSpecification;
        this.responseSpecification = responseSpecification;
    }


    public RestAssuredSetup withPort(int port) {
        RestAssured.port = port;
        requestSpecification.port(port);
        return this;
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public ResponseSpecification getResponseSpecification() {
        return responseSpecification;
    }
}

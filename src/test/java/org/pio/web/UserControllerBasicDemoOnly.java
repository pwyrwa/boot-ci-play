package org.pio.web;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pio.PioJacksonApplication;
import org.pio.service.LocalUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

/**
 * Basic Rest Assured demo only
 * <p/>
 * Created 2/3/16.
 */

public class UserControllerBasicDemoOnly {


    int port = 8080;


    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void getUser_basic_ok() {
        given()
                   .log().all()
                   .contentType(ContentType.JSON.withCharset("UTF-8"))
                   .header(HttpHeaders.ACCEPT, ContentType.JSON.toString())
                   .pathParam("userName", LocalUserRepository.CONSTANT_USER)
                .when()
                    .get("/user/{userName}")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.OK.value())
                    .contentType("application/json;charset=UTF-8")
                 .body("userName", equalTo("pio"))
                 .body("firstName", equalTo("Piotr"))
                 .body("lastName", equalTo("W"));
    }
}

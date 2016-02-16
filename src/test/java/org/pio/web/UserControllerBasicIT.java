package org.pio.web;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.requestSpecification;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.pio.PioJacksonApplication;
import org.pio.service.LocalUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

/**
 * Basic Rest Assured usage
 * <p/>
 * Created 2/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PioJacksonApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class UserControllerBasicIT {

    @Value("${local.server.port}")
    int port;


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

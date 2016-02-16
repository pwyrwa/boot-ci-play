package org.pio.web;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.requestSpecification;
import static org.hamcrest.core.IsEqual.equalTo;

import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pio.PioJacksonApplication;
import org.pio.service.LocalUserRepository;
import org.pio.testconfig.RestAssuredSetup;
import org.pio.testconfig.TestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

/**
 * Basic Rest Assured usage
 * <p/>
 * Created 2/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration( PioJacksonApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class UserControllerRequestSpecIT {


    @Autowired
    RestAssuredSetup restAssuredSetup;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        restAssuredSetup.withPort(port);
    }

    @Test
    public void getUser_ok() {
        given()
                .spec(restAssuredSetup.getRequestSpecification())
                .pathParam("userName", LocalUserRepository.CONSTANT_USER)
            .when()
                .get("/user/{userName}")
            .then()
                .spec(restAssuredSetup.getResponseSpecification())
                .statusCode(HttpStatus.OK.value())
                .body("userName", equalTo("pio"))
                .body("firstName", equalTo("Piotr"))
                .body("lastName", equalTo("W"));
    }

    @Test
    public void getUser_notfound_404() {
        given()
                .spec(restAssuredSetup.getRequestSpecification())
                .pathParam("userName", "bad")
            .when()
                .get("/user/{userName}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}

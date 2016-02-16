package org.pio.testconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

/**
 * Test configuration for tests
 * <p/>
 * Created 2/15/16.
 */
@Configuration
public class TestConfiguration {

    @Bean
    public RestAssuredSetup restAssurredSetup() {
        RestAssured.baseURI = "http://localhost";

        RequestSpecBuilder builder = new RequestSpecBuilder().log(LogDetail.ALL);

        RequestSpecification requestSpec = builder
                .setContentType(ContentType.JSON.withCharset("UTF-8"))
                .addHeader(HttpHeaders.ACCEPT, ContentType.JSON.toString())
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectHeader("Content-Type", "application/json;charset=UTF-8")
                .build();

        return new RestAssuredSetup(requestSpec, responseSpecification);

    }

}

package org.pio.web;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.naming.ServiceUnavailableException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pio.PioJacksonApplication;
import org.pio.error.RequiredServiceNotReachebleException;
import org.pio.service.LocalUserRepository;
import org.pio.service.UserRepository;
import org.pio.testconfig.RestAssuredSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Override bean in context with broken one
 * <p/>
 * Created 2/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration( {PioJacksonApplication.class})
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext
@ActiveProfiles("broken-user-repository")
public class UserControllerUnreachableRepositoryErrorIT {

    static final String USER_REPOSITORY_NOT_REACHABLE = "User repository not reachable";
    @Autowired
    RestAssuredSetup restAssuredSetup;

    @Configuration
    static class OverrideRepository {

        public OverrideRepository() {}

        @Bean
        @Primary
        @Profile("broken-user-repository")
        public UserRepository brokenRespository() {
            UserRepository brokenRepository = mock(UserRepository.class);
            when(brokenRepository.getUser(eq(LocalUserRepository.CONSTANT_USER))).thenThrow(
                    new RequiredServiceNotReachebleException(RequiredServiceNotReachebleException.MODULE.USER_REPOSITORY,
                            UserControllerUnreachableRepositoryErrorIT.USER_REPOSITORY_NOT_REACHABLE));
            return brokenRepository;

        }
    }

    @Value("${local.server.port}")
    int port;


    @Before
    public void setUp() throws Exception {
        restAssuredSetup.withPort(port);

    }

    @Test
    public void getUser_unreachable_repository() {
        given()
                .spec(restAssuredSetup.getRequestSpecification())
                .pathParam("userName", LocalUserRepository.CONSTANT_USER)
            .when()
                .get("/user/{userName}")
            .then()
                .spec(restAssuredSetup.getResponseSpecification())
                .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                .log().all()
                .body("errorType", equalTo("SERVICE_UNAVAILABLE"))
                .body("module", equalTo(RequiredServiceNotReachebleException.MODULE.USER_REPOSITORY.toString()))
                .body("message", equalTo(USER_REPOSITORY_NOT_REACHABLE));
    }

}




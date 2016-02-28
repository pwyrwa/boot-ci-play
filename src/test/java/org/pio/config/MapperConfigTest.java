package org.pio.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pio.PioJacksonApplication;
import org.pio.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PioJacksonApplication.class)
public class MapperConfigTest {

    @Autowired
    @Qualifier("v3Mapper")
    ObjectMapper mapperV3;

//    @Autowired
//    @Qualifier("v4Mapper")
//    ObjectMapper mapperV4;

    @Test
    public void overrideDefaults() throws JsonProcessingException {

        String jsonUser = mapperV3.writeValueAsString(new User("pedro", "Piotr", "W"));
        System.out.println("jsonUser v3 = " + jsonUser);

        FilterProvider filters = new SimpleFilterProvider().addFilter("filter properties by name", SimpleBeanPropertyFilter
                .serializeAllExcept("firstName"));

//        jsonUser = mapperV4.writer(filters).writeValueAsString(new User("pedro", "Piotr", "W"));
//        System.out.println("jsonUser V4 = " + jsonUser);


//        assertThat(jsonUser)
//                .contains("\"firstName\":\"Piotr\"")
//                .contains("\"lastName\":\"W\"")
//                .contains("\"userName\":\"pedro\"");

    }



}

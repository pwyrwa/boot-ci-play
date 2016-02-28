package org.pio.config;

import org.pio.mixin.UserMixin_v3;
import org.pio.mixin.UserMixin_v4;
import org.pio.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 */
@Configuration
public class MapperConfig {

    private final static Logger LOG = LoggerFactory.getLogger(MapperConfig.class);

    @Bean(name="v3Mapper")
    public ObjectMapper v3Mapper() {
        LOG.info("Creating mapper");

        ObjectMapper mapper = new ObjectMapper();

        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NONE);

        mapper.addMixIn(User.class, UserMixin_v3.class);
        return mapper;
    }

//    @Bean(name="v4Mapper")
//    public ObjectMapper v4Mapper() {
//        LOG.info("Creating mapper");
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
//        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NONE);
//
//        mapper.addMixIn(User.class, UserMixin_v4.class);
//        return mapper;
//    }


    @Bean
    @Autowired
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(@Qualifier("v3Mapper") ObjectMapper objectMapper) {
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }


}

package org.pio.mixin;

import org.pio.model.User;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Mixin for user class
 *
 * <p/>
 * Created 1/29/16.
 */

public interface UserMixin_v3 {

    @JsonProperty("userName")
    String getUserName();

    @JsonProperty("firstName")
    String getFirstName();

    @JsonProperty("lastName")
    String getLastName();

}

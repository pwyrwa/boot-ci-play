package org.pio.mixin;

import org.pio.model.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Mixin for user class
 *
 * <p/>
 * Created 1/29/16.
 */


public interface UserMixin_v4 {


    @JsonProperty("userName")
    String getUserName();

    @JsonProperty("nameV4")
    String getFirstName();

    @JsonProperty("lastName")
    String getLastName();


}

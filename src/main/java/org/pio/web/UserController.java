package org.pio.web;

import java.util.Optional;


import javax.websocket.server.PathParam;

import org.pio.model.User;
import org.pio.service.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller
 * <p/>
 * Created 2/3/16.
 */
@RestController
public class UserController {

    private static Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/user/{userName}")
    @ResponseBody
    public ResponseEntity<User> userByUserName(@PathVariable("userName") String userName)
    {
        LOG.info("Calling userByUserName for {}", userName);
        Optional<User> userResponse = userRepository.getUser(userName);

        if (userResponse.isPresent()) {
            return ResponseEntity.ok(userResponse.get());
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

}

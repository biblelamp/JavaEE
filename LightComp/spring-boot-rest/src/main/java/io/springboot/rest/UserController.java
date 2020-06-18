package io.springboot.rest;

import io.springboot.rest.api.rest.UserApi;
import io.springboot.rest.api.rest.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class UserController implements UserApi {

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        User user = new User();
        user.setId(id);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setGender(User.GenderEnum.MALE);
        user.setDateOfBirth("01-01-1970");
        return ResponseEntity.ok(user);
    }
}
